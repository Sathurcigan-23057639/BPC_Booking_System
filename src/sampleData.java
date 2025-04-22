public class sampleData {

    public static void initializeData() {
        // Adding sample Physiotherapists
        Physiotherapist p1 = new Physiotherapist(1, "Michale", "USA", "87656656789", "Physiotherapy");
        p1.addTreatment("Massage");
        p1.addAvailability("Monday", "10:00-11:00");
        p1.addAvailability("Tuesday", "13:00-15:00");
        p1.addAvailability("Friday", "9:00-11:00");
        BookingHandlingSystem.getInstance().getPhysiotherapists().add(p1);

        Physiotherapist p2 = new Physiotherapist(2, "Tom", "Australia", "987654455678", "Osteopathy");
        p2.addTreatment("Acupuncture");
        p2.addAvailability("Tuesday", "15:00-16:00");
        p2.addAvailability("Friday", "12:00-16:00");
        BookingHandlingSystem.getInstance().getPhysiotherapists().add(p2);

        Physiotherapist p3 = new Physiotherapist(3, "Arul", "UK", "8765678789", "Rehabilitation");
        p3.addTreatment("Pool rehabilitation");
        p3.addAvailability("Monday", "15:00-16:00");
        p3.addAvailability("Wednesday", "10:00-12:00");
        BookingHandlingSystem.getInstance().getPhysiotherapists().add(p3);

        // Adding sample Patients
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(1, "Abi", "UK", "7654456789"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(2, "Chris", "Finland", "3456545678"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(3, "Robert", "USA", "1345654323"));
    }
}
