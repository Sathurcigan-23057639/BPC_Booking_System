import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class BookingHandlingSystemTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void getInstance() {
        BookingHandlingSystem bhs1 = BookingHandlingSystem.getInstance();
        BookingHandlingSystem bhs2 = BookingHandlingSystem.getInstance();

        System.out.println("getInstance() Function Testing\n-------------------------");
        assertSame(bhs1, bhs2);
    }

    @Test
    void getPatients() {
        Patient patient = new Patient(1, "Abi", "UK", "7654456789");
        BookingHandlingSystem.getInstance().getPatients().add(patient);

        Patient actualResult = BookingHandlingSystem.getInstance().getPatients().getFirst();

        System.out.println("getPatients() Function Testing\n-------------------------");
        assertEquals(patient, actualResult);
    }

    @Test
    void getPhysiotherapists() {
        Physiotherapist Physio = new Physiotherapist(5, "Johnny", "UK", "87676789098", "Osteopathy");
        Physio.addTreatment("Visceral Osteopathy");
        Physio.addAvailability("Monday", "15:00-16:00");
        Physio.addAvailability("Friday", "13:00-15:00");
        BookingHandlingSystem.getInstance().getPhysiotherapists().add(Physio);

        Physiotherapist actualResult = BookingHandlingSystem.getInstance().getPhysiotherapists().getFirst();

        System.out.println("getPhysiotherapists() Function Testing\n-------------------------");
        assertEquals(Physio, actualResult);
    }

    @Test
    void getAppointments() {
        Patient patient = new Patient(1, "Abi", "UK", "7654456789");

        Physiotherapist Physio = new Physiotherapist(5, "Johnny", "UK", "87676789098", "Osteopathy");
        Physio.addTreatment("Visceral Osteopathy");
        Physio.addAvailability("Monday", "15:00-16:00");
        Physio.addAvailability("Friday", "13:00-15:00");
        BookingHandlingSystem.getInstance().getPhysiotherapists().add(Physio);

        Appointment appointment = new Appointment(10, patient,Physio,Physio.getExpertise(),Physio.getTreatments().getFirst(),Physio.getAvailability().get(0));
        BookingHandlingSystem.getInstance().getAppointments().add(appointment);

        Appointment actualResult = BookingHandlingSystem.getInstance().getAppointments().getFirst();
        System.out.println("getAppointments() Function Testing\n-------------------------");
        assertEquals(appointment, actualResult);
    }
}