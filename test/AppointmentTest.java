import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AppointmentTest {

    private void dataInitialize() {
        sampleData.initializeData();
        BookingHandlingSystem.getInstance().getAppointments().add(
                new Appointment(1, BookingHandlingSystem.getInstance().getPatients().get(1),
                        BookingHandlingSystem.getInstance().getPhysiotherapists().get(3),
                        BookingHandlingSystem.getInstance().getPhysiotherapists().get(3).getExpertise(),
                        BookingHandlingSystem.getInstance().getPhysiotherapists().get(3).getTreatments().getFirst(),
                        "12:00-14:00")
        );
    }

    @Test
    void getId() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        int expectedResult = 1;
        int actualResult = Objects.requireNonNull(appointment).getId();

        System.out.println("getId() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPatient() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        Patient expectedResult = BookingHandlingSystem.getInstance().getPatients().get(1);
        Patient actualResult = Objects.requireNonNull(appointment).getPatient();

        System.out.println("getPatient() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPhysiotherapist() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        Physiotherapist expectedResult = BookingHandlingSystem.getInstance().getPhysiotherapists().get(3);
        Physiotherapist actualResult = Objects.requireNonNull(appointment).getPhysiotherapist();

        System.out.println("getPhysiotherapist() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTreatment() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        Physiotherapist expectedResult = BookingHandlingSystem.getInstance().getPhysiotherapists().get(3);
        Physiotherapist actualResult = Objects.requireNonNull(appointment).getPhysiotherapist();

        System.out.println("getTreatment() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getExpertise() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        String expectedResult = BookingHandlingSystem.getInstance().getPhysiotherapists().get(3).getExpertise();
        String actualResult = Objects.requireNonNull(appointment).getPhysiotherapist().getExpertise();

        System.out.println("getExpertise() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTime() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        String expectedResult = BookingHandlingSystem.getInstance().getPhysiotherapists().get(3).getAvailability().get(0);
        String actualResult = Objects.requireNonNull(appointment).getPhysiotherapist().getAvailability().get(0);

        System.out.println("getTime() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setTime() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);
        Objects.requireNonNull(appointment).setTime("13:00-14:00");

        String expectedResult = "13:00-14:00";
        String actualResult = appointment.getTime();

        System.out.println("setTime() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getStatus() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        String expectedResult = "Booked";
        String actualResult = Objects.requireNonNull(appointment).getStatus();

        System.out.println("getStatus() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setStatus() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);
        Objects.requireNonNull(appointment).setStatus("Booked");

        String expectedResult = "Booked";
        String actualResult = Objects.requireNonNull(appointment).getStatus();

        System.out.println("setStatus() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToString() {
        dataInitialize();
        List<Appointment> patientAppointments = BookingHandlingSystem.getInstance().getAppointments().stream().filter(a -> a.getPatient().equals(BookingHandlingSystem.getInstance().getPatients().get(1))).toList();
        Appointment appointment = patientAppointments.stream().filter(a -> a.getId() == 1).findFirst().orElse(null);

        String expectedResult = "Appointment ID: 1, Patient: Chris, Physiotherapist: varun, Expertise: Physiotherapy, Treatment: Musculoskeletal Physiotherapy, Time: 12:00-14:00, Status: Booked";
        String actualResult = Objects.requireNonNull(appointment).toString();

        System.out.println("testToString() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }
}