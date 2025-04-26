import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PhysiotherapistDashboard {
    private static final Scanner scanner = new Scanner(System.in);

    public static void physiotherapistMenu() {
        System.out.println("\n---------------------------------------------------");
        System.out.println("              Physiotherapist Dashboard");
        System.out.println("---------------------------------------------------");
        System.out.println("1. Create new Physiotherapist Profile");
        System.out.println("2. Find my Profile");
        System.out.println("3. Generate Report");
        System.out.println("4. Back to Main Menu");
        System.out.print("Select an option: ");
        int choice = 0;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a valid choice.");
            scanner.next();
            physiotherapistMenu();
        }

        switch (choice) {
            case 1 -> createPhysiotherapist();
            case 2 -> findPhysiotherapist();
            case 3 -> generateReport();
            case 4 -> BookingHandlingSystem.backToMainPage();
            default -> {
                System.out.println("Invalid option. Please Try Again..!.");
                physiotherapistMenu();
            }
        }
    }

    private static void createPhysiotherapist() {
        System.out.print("\nEnter Full Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Telephone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Expertise: ");
        String expertise = scanner.nextLine();

        // Create a new physiotherapist
        if (User.isValidNumber(phone)) {
            int id = BookingHandlingSystem.getInstance().getPhysiotherapists().size() + 1;
            Physiotherapist physio = new Physiotherapist(id, name.substring(0, 1).toUpperCase() + name.substring(1), address.substring(0, 1).toUpperCase() + address.substring(1), phone, expertise.substring(0, 1).toUpperCase() + expertise.substring(1));

            // Add treatments for the expertise
            System.out.println("Enter the Treatments for this specific Expertise \n[Type 'done' to finish]..!");
            int no = 1;
            while (true) {
                System.out.print("Enter Treatment "+no+": ");
                no = no + 1;
                String treatment = scanner.nextLine();
                if (treatment.equalsIgnoreCase("done")) break;
                physio.addTreatment(treatment);
            }

            // Add available time slots for treatments
            System.out.println("Enter your Available Time slots \n[Type 'done' to finish]..!");
            while (true) {
                System.out.print("Enter Day: ");
                String day = scanner.nextLine();
                if (day.equalsIgnoreCase("done")) break;

                System.out.print("Enter Time Slot: ");
                String time = scanner.nextLine();
                physio.addAvailability(day.substring(0, 1).toUpperCase() + day.substring(1), time);
            }

            BookingHandlingSystem.getInstance().getPhysiotherapists().add(physio);
            System.out.println("\nPhysiotherapist Profile created successfully..! and your Physiotherapist ID is " + physio.getId());
        } else {
            System.out.print("Please enter a valid phone number and try again..!\n");
            physiotherapistMenu();
        }
        physiotherapistMenu();
    }

    private static void findPhysiotherapist() {
        System.out.println("\n---------------------------------------------------");
        System.out.println("              Physiotherapist Profile              ");
        System.out.println("---------------------------------------------------");
        System.out.println("1. Search by Physiotherapist ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Back");
        System.out.print("Select an option: ");
        int choice = 0;

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a valid choice.");
            scanner.next();
            physiotherapistMenu();
        }

        switch (choice) {
            case 1 -> searchPhysiotherapistById();
            case 2 -> searchPhysiotherapistByName();
            case 3 -> physiotherapistMenu();
            default -> {
                System.out.println("Invalid option. Please Try Again..!..!");
                findPhysiotherapist();
            }
        }
    }

    private static void searchPhysiotherapistByName() {
        System.out.print("Enter Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        Physiotherapist physio = BookingHandlingSystem.getInstance().getPhysiotherapists().stream().filter(p -> p.getName().toLowerCase().trim().equalsIgnoreCase(name.toLowerCase())).findFirst().orElse(null);

        if (physio != null) {
            updatePhysioProfile(physio);
        } else {
            System.out.println("Physiotherapist not found..!");
            physiotherapistMenu();
        }
    }

    private static void searchPhysiotherapistById() {
        System.out.print("Enter Physiotherapist ID: ");
        int id;

        if (scanner.hasNextInt()) {
            id = scanner.nextInt();

            Physiotherapist physio = BookingHandlingSystem.getInstance().getPhysiotherapists().stream().filter(p -> p.getId() == id).findFirst().orElse(null);

            if (physio != null) {
                updatePhysioProfile(physio);
            } else {
                System.out.println("Physiotherapist not found.");
                physiotherapistMenu();
            }
        } else {
            System.out.println("Invalid input! Please enter a valid Physiotherapist ID..!");
            scanner.next();
            physiotherapistMenu();
        }
    }

    private static void updatePhysioProfile(Physiotherapist physio) {
        ArrayList<String> names = new ArrayList<>();
        for (Map.Entry<String, String> slot : physio.getAvailability().entrySet()) {
            String day = AppointmentHandling.convertDate(slot.getKey());
            String time = slot.getValue();
            String fullTime = day + " " + time;
            names.add(fullTime);
        }
        System.out.println("=> Physiotherapist ID: "+physio.getId()+", Name: "+physio.getName()+", Address: "+physio.getAddress()+", Phone: "+physio.getPhone()+", Expertise: "+physio.getExpertise()+", Treatments: "+physio.getTreatments()+", Availability: "+names);
        System.out.print("Do you want to modify available time slots? (yes/no): ");
        scanner.nextLine();
        String choice = scanner.nextLine();

        if (choice.toLowerCase().trim().equalsIgnoreCase("yes")) {
            System.out.println("Enter new Available Time slots \n[Type 'done' to finish]");
            while (true) {
                System.out.print("Enter Day: ");
                String day = scanner.nextLine();
                if (day.equalsIgnoreCase("done")) break;

                System.out.print("Enter Time Slot: ");
                String time = scanner.nextLine();
                physio.addAvailability(day.substring(0, 1).toUpperCase() + day.substring(1), time);
            }
            System.out.println("Availability updated successfully!");
        }
        else{
            System.out.println("Invalid input. Try again..!");
        }
        physiotherapistMenu();
    }

    private static void generateReport() {
        System.out.println("\n---------------------------------------------------");
        System.out.println("            Appointment Detailed Report");
        System.out.println("---------------------------------------------------");

        BookingHandlingSystem system = BookingHandlingSystem.getInstance();
        system.getPhysiotherapists().stream()
                .sorted((p1, p2) -> {
                    long attended1 = system.getAppointments().stream()
                            .filter(a -> a.getPhysiotherapist().equals(p1))
                            .filter(a -> "Attended".equalsIgnoreCase(a.getStatus()))
                            .count();

                    long attended2 = system.getAppointments().stream()
                            .filter(a -> a.getPhysiotherapist().equals(p2))
                            .filter(a -> "Attended".equalsIgnoreCase(a.getStatus()))
                            .count();

                    return Long.compare(attended2, attended1);
                })
                .forEach(physio -> {
                    System.out.println("Physiotherapist: Dr." + physio.getName());

                    List<Appointment> physioAppointments = system.getAppointments().stream()
                            .filter(a -> a.getPhysiotherapist().equals(physio))
                            .toList();

                    long attendedCount = physioAppointments.stream()
                            .filter(a -> "Attended".equalsIgnoreCase(a.getStatus()))
                            .count();

                    long cancelledCount = physioAppointments.stream()
                            .filter(a -> "Cancelled".equalsIgnoreCase(a.getStatus()))
                            .count();

                    long bookedCount = physioAppointments.size();

                    physioAppointments.forEach(a -> System.out.println("=> " + a));
                    System.out.println("Number of Attended Appointments: " + attendedCount);
                    System.out.println("Number of Cancelled Appointments: " + cancelledCount);
                    System.out.println("Total Appointments: " + bookedCount + "\n");
                });
        physiotherapistMenu();
    }
}
