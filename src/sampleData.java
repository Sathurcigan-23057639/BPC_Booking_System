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

        Physiotherapist p4 = new Physiotherapist(4, "Varun", "UK", "98765678767", "Physiotherapy");
        p4.addTreatment("Massage");
        p4.addAvailability("Monday", "12:00-14:00");
        p4.addAvailability("Wednesday", "09:00-11:00");
        BookingHandlingSystem.getInstance().getPhysiotherapists().add(p4);

        Physiotherapist p5 = new Physiotherapist(5, "Johnny", "UK", "87676789098", "Osteopathy");
        p5.addTreatment("Visceral Osteopathy");
        p5.addAvailability("Monday", "15:00-16:00");
        p5.addAvailability("Friday", "13:00-15:00");
        BookingHandlingSystem.getInstance().getPhysiotherapists().add(p5);

        // Adding sample Patients
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(1, "Abi", "UK", "7654456789"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(2, "Chris", "Finland", "3456545678"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(3, "Robert", "USA", "1345654323"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(4, "Surya", "Australia", "3456545678"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(5, "Ashok", "Canada", "1345654323"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(6, "Kevin", "UK", "7654456789"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(7, "Suman", "UK", "3456545678"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(8, "Pavi", "UK", "1345654323"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(9, "Shawn", "UK", "3456545678"));
        BookingHandlingSystem.getInstance().getPatients().add(new Patient(10, "Peter", "USA", "1345654323"));
    }
}
