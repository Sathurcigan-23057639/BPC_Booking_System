import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingHandlingSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static BookingHandlingSystem instance;
    private final List<Patient> patients;
    private final List<Physiotherapist> physiotherapists;
    private final List<Appointment> appointments;

    private BookingHandlingSystem() {
        this.patients = new ArrayList<>();
        this.physiotherapists = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public static synchronized BookingHandlingSystem getInstance() {
        if (instance == null) {
            instance = new BookingHandlingSystem();
        }
        return instance;
    }

    public static void main(String[] args) {
        sampleData.initializeData();
        MainPage();
    }

    private static void MainPage() {
        int choice = 0;

        System.out.println("\n---------------------------------------------------");
        System.out.println("         Welcome to BPC Booking System");
        System.out.println("---------------------------------------------------");
        System.out.println("1. Login as Patient");
        System.out.println("2. Login as Physiotherapist");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");

        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        } else {
            System.out.println("Invalid input! Please enter a valid choice.");
            scanner.next();
            MainPage();
        }

        switch (choice) {
            case 1 -> PatientDashboard.patientMenu();
            case 2 -> PhysiotherapistDashboard.physiotherapistMenu();
            case 3 -> close();
            default -> {
                System.out.println("Invalid choice. Try again.");
                MainPage();
            }
        }
    }

    private static void close() {
        System.out.println("\nThanks for using BPC Booking System..!");
        System.exit(0);
    }

    public static void backToMainPage() {
        System.out.println("\nReturning to Main Menu...!");
        MainPage();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Physiotherapist> getPhysiotherapists() {
        return physiotherapists;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

}