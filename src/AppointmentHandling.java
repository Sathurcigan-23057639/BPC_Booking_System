import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AppointmentHandling {
    private static final Scanner scanner = new Scanner(System.in);

    public static void manageAppointments(Patient patient) {
        System.out.println("\nPatient Profile: " + patient);
        handleAppointment(patient);
    }

    private static void handleAppointment(Patient patient) {
        System.out.println("\nWould you like to...");
        System.out.println(" 1. Book Appointment");
        System.out.println(" 2. View Appointments");
        System.out.println(" 3. Back");
        System.out.print(" Select an option: ");
        int choice = 0;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a valid choice..!");
            scanner.next();
            handleAppointment(patient);
        }

        switch (choice) {
            case 1 -> bookAppointment(patient);
            case 2 -> viewAppointments(patient);
            case 3 -> PatientDashboard.patientMenu();
            default -> {
                System.out.println("Invalid option. Try Again..!");
                handleAppointment(patient);
            }
        }
    }

    private static void viewAppointments(Patient patient) {
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(patient)).toList();

        if (patientAppointments.isEmpty()) {
            System.out.println("\n No appointments found..!");
            handleAppointment(patient);
        } else {
            System.out.println("\n Your Appointments:");
            patientAppointments.forEach(a -> System.out.println("=> " + a));

            System.out.println("\n 1. Process Appointment");
            System.out.println(" 2. Back");
            System.out.print(" Select an option: ");
            int option;

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                switch (option) {
                    case 1 -> processAppointment(patient);
                    case 2 -> handleAppointment(patient);
                    default -> {
                        System.out.println("Invalid option. Try Again..!");
                        handleAppointment(patient);
                    }
                }
            } else {
                System.out.println("Invalid input! Please enter a valid option..!");
                scanner.next();
                viewAppointments(patient);
            }
        }
    }

    private static void processAppointment(Patient patient) {
        System.out.print("\n The ID of the appointment you want to process: ");
        int appointmentID;

        if (scanner.hasNextInt()) {
            appointmentID = scanner.nextInt();
            List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(patient) && a.getStatus().equals("Booked")).toList();
            if (!patientAppointments.isEmpty()) {
                System.out.println("\n 1. Attend Appointment");
                System.out.println(" 2. Reschedule Appointment");
                System.out.println(" 3. Cancel Appointment");
                System.out.print(" Select an option: ");
                int choice = 0;

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    System.out.println("Invalid input! Please enter a valid choice..!");
                    scanner.next();
                    handleAppointment(patient);
                }

                switch (choice) {
                    case 1 -> attendAppointment(appointmentID, patient);
                    case 2 -> rescheduleAppointment(patient);
                    case 3 -> cancelAppointment(appointmentID, patient);
                    default -> {
                        System.out.println("Invalid option. Try Again..!");
                        viewAppointments(patient);
                    }
                }
            } else {
                System.out.println(" Invalid Appointment ID..!\n Check the status of the Appointment you are currently prioritising, that might be already completed..!");
                viewAppointments(patient);
            }

        } else {
            System.out.println("Invalid input! Please enter a Appointment ID..!");
            scanner.next();
            viewAppointments(patient);
        }
    }

    private static void attendAppointment(int id, Patient patient) {
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(patient) && a.getStatus().equals("Booked")).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == id).findFirst().orElse(null);

        System.out.print(" Have you Attend the Appointment (yes/no): ");
        scanner.nextLine();
        String answer = scanner.nextLine();

        if (answer.toLowerCase().trim().equalsIgnoreCase("yes")) {
            if (appointment != null) {
                appointment.setStatus("Attended");
                System.out.println("\n Appointment Attended successfully.");
            } else {
                System.out.println(" Invalid Appointment ID..!\n Check the status of the Appointment you are currently prioritising, that might be already completed..!");
            }
        }
        handleAppointment(patient);
    }

    private static void cancelAppointment(int id, Patient patient) {
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(patient) && a.getStatus().equals("Booked")).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == id).findFirst().orElse(null);

        System.out.print(" Do you want to cancel the Appointment (yes/no): ");
        scanner.nextLine();
        String answer = scanner.nextLine();

        if (answer.toLowerCase().trim().equalsIgnoreCase("yes")) {
            if (appointment != null) {
                appointment.setStatus("Cancelled");
                System.out.println("\n Appointment Cancelled successfully..!");
            } else {
                System.out.println(" Invalid Appointment ID..! \n Check the status of the Appointment you are currently prioritising, that might be already completed..!");
            }
        }
        handleAppointment(patient);
    }

    private static void rescheduleAppointment(Patient patient) {
        // Get all appointments for the specific patient
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(patient)).toList();

        if (patientAppointments.isEmpty()) {
            System.out.println("\nNo appointments to reschedule.");
            return;
        }

        System.out.println("\nYour Appointments:");
        patientAppointments.forEach(a -> System.out.println("=> " + a));

        System.out.print("Enter Appointment ID to reschedule: ");
        int appointmentId;

        if (scanner.hasNextInt()) {
            appointmentId = scanner.nextInt();
        } else {
            appointmentId = 0;
            System.out.println("Invalid input! Please enter a valid choice..!");
            scanner.next();
            rescheduleAppointment(patient);
        }

        // Find the appointment to reschedule
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == appointmentId).findFirst().orElse(null);

        if (appointment != null) {
            // Get the physiotherapist for this appointment
            Physiotherapist physiotherapist = appointment.getPhysiotherapist();

            if (physiotherapist != null) {
                boolean rescheduling = true;
                while (rescheduling) {
                    System.out.println("\nAvailable Time Slots for " + physiotherapist.getName() + ":");
                    Map<String, String> availableTimes = physiotherapist.getAvailability();

                    int slotCounter = 1;
                    Map<Integer, String> timeSlotMap = new HashMap<>();

                    for (Map.Entry<String, String> entry : availableTimes.entrySet()) {
                        System.out.println("Timeslot ID: " + slotCounter + "=> Day: " + entry.getKey() + ", Time: " + entry.getValue());
                        timeSlotMap.put(slotCounter, entry.getKey() + "-" + entry.getValue());
                        slotCounter++;
                    }

                    System.out.println("---------------------------------------------------");
                    System.out.println("Enter the Timeslot ID to reschedule or Enter '0' to go back..!");
                    System.out.print("Select an option: ");
                    int selectedId;

                    if (scanner.hasNextInt()) {
                        selectedId = scanner.nextInt();
                        if (selectedId == 0) {
                            rescheduling = false;
                        } else if (timeSlotMap.containsKey(selectedId)) {
                            // Get the new time slot and update the appointment
                            String newTimeSlot = timeSlotMap.get(selectedId);

                            appointment.setTime(newTimeSlot);
                            System.out.println("\nAppointment rescheduled successfully..! \nUpdated Details:");
                            System.out.println(appointment);

                            // Remove the new time slot from the physiotherapist's availability
                            String[] slotParts = newTimeSlot.split("-");
                            physiotherapist.getAvailability().remove(slotParts[0]);

                            rescheduling = false;
                        } else {
                            System.out.println("Invalid ID. Please try again.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter a valid Timeslot ID.");
                        scanner.next();
                        rescheduleAppointment(patient);
                    }
                }
            } else {
                System.out.println("There is no any available Time slots for this Treatment..!");
                handleAppointment(patient);
            }

        } else {
            System.out.println("Invalid Appointment ID.");
        }

        handleAppointment(patient);
    }

    private static void bookAppointment(Patient patient) {
        System.out.println("\n---------------------------------------------------");
        System.out.println("           Book Appointment Options");
        System.out.println("---------------------------------------------------");
        System.out.println("1. Search by Treatment");
        System.out.println("2. Search by Expertise");
        System.out.println("3. Back");
        System.out.print("Select an option: ");
        int choice = 0;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a valid choice..!");
            scanner.next();
            bookAppointment(patient);
        }

        switch (choice) {
            case 1 -> searchAndBookByTreatment(patient);
            case 2 -> searchAndBookByExpertise(patient);
            case 3 -> handleAppointment(patient);
            default -> {
                System.out.println("Invalid option. Please Try Again..!");
                bookAppointment(patient);
            }
        }
    }

    private static void searchAndBookByTreatment(Patient patient) {
        System.out.print("\nEnter Treatment Name: ");
        scanner.nextLine();
        String treatment = scanner.nextLine();
        String requiredTreatment = treatment.substring(0, 1).toUpperCase() + treatment.substring(1);

        List<Appointment> bookedAppointments = BookingHandlingSystem.getInstance().getAppointments();

        List<Physiotherapist> availablePhysios = BookingHandlingSystem.getInstance().getPhysiotherapists().stream().filter(p -> p.getTreatments().contains(requiredTreatment)).toList();

        if (availablePhysios.isEmpty()) {
            System.out.println("No Physiotherapists found offering this Treatment..!");
            bookAppointment(patient);
            return;
        }

        System.out.println("\nAvailable Physiotherapists and their Time Slots:");

        Map<Integer, Map.Entry<String, String>> bookingOptions = new HashMap<>();
        int optionId = 1;

        for (Physiotherapist physio : availablePhysios) {
            for (Map.Entry<String, String> slot : physio.getAvailability().entrySet()) {
                String day = slot.getKey();
                String time = slot.getValue();
                String fullTime = day + " " + time;

                boolean alreadyBooked = bookedAppointments.stream().anyMatch(a -> a.getPhysiotherapist().getId() == physio.getId() && a.getTime().equals(fullTime) && a.getTreatment().equals(requiredTreatment));

                if (!alreadyBooked) {
                    System.out.println(optionId + "=> " + physio.getName() + " - " + physio.getExpertise() + " - " + physio.getTreatments() + " | Slot: " + fullTime);
                    bookingOptions.put(optionId, Map.entry(physio.getId() + "", fullTime));
                    optionId++;
                }
            }
        }

        if (bookingOptions.isEmpty()) {
            System.out.println("No available Time slots for this Treatment..!");
            return;
        }

        System.out.print("\nSelect an option to book a Time slot: ");
        int selection;

        if (scanner.hasNextInt()) {
            selection = scanner.nextInt();

            Map.Entry<String, String> selected = bookingOptions.get(selection);

            if (selected == null) {
                System.out.println("Invalid selection.");
                return;
            }

            Physiotherapist selectedPhysio = BookingHandlingSystem.getInstance().getPhysiotherapists().stream().filter(p -> String.valueOf(p.getId()).equals(selected.getKey())).findFirst().orElse(null);

            if (selectedPhysio == null) {
                System.out.println("Error: Physiotherapist not found.");
                return;
            }

            // Book the appointment
            List<Appointment> allAppointments = BookingHandlingSystem.getInstance().getAppointments();
            int newId = allAppointments.stream().mapToInt(Appointment::getId).max().orElse(0) + 1;

            Appointment appointment = new Appointment(newId, patient, selectedPhysio, selectedPhysio.getExpertise(), requiredTreatment, selected.getValue());
            allAppointments.add(appointment);

            // Remove the booked time slot
            String[] split = selected.getValue().split(" ");
            selectedPhysio.getAvailability().remove(split[0]);

            System.out.println("\nAppointment booked successfully..!");
            System.out.println("=> " + appointment);
        } else {
            System.out.println("Invalid input! Please enter a valid option..!");
            scanner.next();
            searchAndBookByTreatment(patient);
        }

        PatientDashboard.patientMenu();
    }

    private static void searchAndBookByExpertise(Patient patient) {
        System.out.print("\nEnter the Expertise: ");
        scanner.nextLine();
        String expertise = scanner.nextLine();
        String requiredExpertise = expertise.substring(0, 1).toUpperCase() + expertise.substring(1);

        List<Appointment> bookedAppointments = BookingHandlingSystem.getInstance().getAppointments();

        List<Physiotherapist> availablePhysios = BookingHandlingSystem.getInstance().getPhysiotherapists().stream().filter(p -> p.getExpertise().contains(requiredExpertise)).toList();

        if (availablePhysios.isEmpty()) {
            System.out.println("No Physiotherapists found with this Expertise..!");
            bookAppointment(patient);
            return;
        }

        System.out.println("\nAvailable Physiotherapists and their Time Slots:");

        Map<Integer, Map.Entry<String, String>> bookingOptions = new HashMap<>();
        int optionId = 1;

        for (Physiotherapist physio : availablePhysios) {
            for (Map.Entry<String, String> slot : physio.getAvailability().entrySet()) {
                String day = slot.getKey();
                String time = slot.getValue();
                String fullTime = day + " " + time;

                boolean alreadyBooked = bookedAppointments.stream().anyMatch(a -> a.getPhysiotherapist().getId() == physio.getId() && a.getTime().equals(fullTime) && a.getExpertise().equals(requiredExpertise));

                if (!alreadyBooked) {
                    System.out.println(optionId + "=> " + physio.getName() + " - " + physio.getExpertise() + " - " + physio.getTreatments() + " | Slot: " + fullTime);
                    bookingOptions.put(optionId, Map.entry(physio.getId() + "", fullTime));
                    optionId++;
                }
            }
        }

        if (bookingOptions.isEmpty()) {
            System.out.println("No available Time slots for this Expertise..!");
            return;
        }

        System.out.print("\nSelect an option to book a Time slot for this Expertise: ");
        int selection;

        if (scanner.hasNextInt()) {
            selection = scanner.nextInt();

            Map.Entry<String, String> selected = bookingOptions.get(selection);

            if (selected == null) {
                System.out.println("Invalid selection..!");
                return;
            }

            Physiotherapist chosenPhysio = BookingHandlingSystem.getInstance().getPhysiotherapists().stream().filter(p -> String.valueOf(p.getId()).equals(selected.getKey())).findFirst().orElse(null);

            if (chosenPhysio == null) {
                System.out.println("Error: Physiotherapist not found.");
                return;
            }

            // Book the appointment
            List<Appointment> allAppointments = BookingHandlingSystem.getInstance().getAppointments();
            int newId = allAppointments.stream().mapToInt(Appointment::getId).max().orElse(0) + 1;

            Appointment appointment = new Appointment(newId, patient, chosenPhysio, chosenPhysio.getExpertise(), requiredExpertise, selected.getValue());
            allAppointments.add(appointment);

            // Remove the booked time slot
            String[] split = selected.getValue().split(" ");
            chosenPhysio.getAvailability().remove(split[0]);

            System.out.println("\nAppointment booked successfully..!");
            System.out.println("=> " + appointment);
        } else {
            System.out.println("Invalid input! Please enter a valid option..!");
            scanner.next();
            searchAndBookByTreatment(patient);
        }

        PatientDashboard.patientMenu();
    }

}
