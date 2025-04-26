import java.util.Scanner;

public class PatientDashboard {
    private static final Scanner scanner = new Scanner(System.in);

    public static void patientMenu() {
        int choice = 0;
        System.out.println("\n---------------------------------------------------");
        System.out.println("                  Patient Dashboard");
        System.out.println("---------------------------------------------------");
        System.out.println("1. Create new Patient Profile");
        System.out.println("2. Find my Profile");
        System.out.println("3. Back to Main Menu");
        System.out.print("Select an option: ");

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a valid choice.");
            scanner.next();
            patientMenu();
        }

        switch (choice) {
            case 1 -> createPatient();
            case 2 -> findPatient();
            case 3 -> BookingHandlingSystem.backToMainPage();
            default -> {
                System.out.println("Invalid choice. Try again.");
                patientMenu();
            }
        }
    }

    private static void createPatient() {
        System.out.print("\nEnter your Full Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Enter your Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter your Telephone Number: ");
        String phone = scanner.nextLine();

        if (User.isValidNumber(phone)) {
            int id = BookingHandlingSystem.getInstance().getPatients().size() + 1;
            Patient patient = new Patient(id, name.substring(0, 1).toUpperCase() + name.substring(1), address.substring(0, 1).toUpperCase() + address.substring(1), phone);
            BookingHandlingSystem.getInstance().getPatients().add(patient);
            System.out.println("\nPatient Profile has been created successfully...! \nYour ID is: " + id);
            patientMenu();
        } else {
            System.out.print("Please enter a valid phone number and Try again..!");
            patientMenu();
        }
    }

    protected static void findPatient() {
        int choice = 0;
        System.out.println("\n1. Search by Patient ID");
        System.out.println("2. Search by Name");
        System.out.print("Select an option: ");

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a valid choice..!");
            scanner.next();
            findPatient();
        }

        Patient patient = null;
        switch (choice) {
            case 1 -> {
                System.out.print("\nEnter Patient ID: ");
                int id;

                if (scanner.hasNextInt()) {
                    id = scanner.nextInt();
                } else {
                    id = 0;
                    System.out.println("Invalid input..! Please enter a valid Patient ID..!");
                    scanner.next();
                    patientMenu();
                }
                patient = BookingHandlingSystem.getInstance().getPatients().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
            }
            case 2 -> {
                System.out.print("\nEnter Name: ");
                scanner.nextLine();
                String name = scanner.nextLine();
                    patient = BookingHandlingSystem.getInstance().getPatients().stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
            }
            default -> {
                System.out.println("Invalid choice. Try again..!");
                patientMenu();
            }
        }

        if (patient != null) {
            AppointmentHandling.manageAppointments(patient);
        } else {
            System.out.println("Patient profile not found..!");
            patientMenu();
        }
    }
}
