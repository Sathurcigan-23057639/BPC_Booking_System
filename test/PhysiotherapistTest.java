import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PhysiotherapistTest {

    private Physiotherapist getPhysiotherapist() {
        Physiotherapist physio = new Physiotherapist(15, "Varun", "UK", "098765456789", "Physiotherapy");
        physio.addTreatment("Neurological Physiotherapy");
        return  physio;
    }

    @Test
    void getNextWeekday() {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek targetDay = Physiotherapist.getDayOfWeekFromString("monday");

        LocalDate expectedResult = LocalDate.parse("2025-04-28");
        LocalDate actualResult = Physiotherapist.getNextWeekday(currentDate, targetDay);

        System.out.println("getTime() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getDayOfWeekFromString() {
        String expectedResult = "MONDAY";
        DayOfWeek actualResult = Physiotherapist.getDayOfWeekFromString("monday");

        System.out.println("getDayOfWeekFromString() Function Testing\n-------------------------");
        assertEquals(expectedResult, String.valueOf(actualResult));
    }

    @Test
    void getId() {
        Physiotherapist physio = getPhysiotherapist();

        int expectedResult = 15;
        int actualResult = physio.getId();

        System.out.println("getId() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getExpertise() {
        Physiotherapist physio = getPhysiotherapist();

        String expectedResult = "Physiotherapy";
        String actualResult = physio.getExpertise();

        System.out.println("getExpertise() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getTreatments() {
        Physiotherapist physio = getPhysiotherapist();

        String expectedResult = "Neurological Physiotherapy";
        String actualResult = physio.getTreatments().getFirst();

        System.out.println("getTreatments() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void addTreatment() {
        Physiotherapist physio = getPhysiotherapist();
        physio.addTreatment("Structural Osteopathy");

        String expectedResult = "Structural Osteopathy";
        String actualResult = physio.getTreatments().getLast();

        System.out.println("addTreatment() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAvailability() {
        Physiotherapist physio = getPhysiotherapist();
        physio.addAvailability("Tuesday", "07:00-09:00");

        String availableSlots = physio.getAvailability().entrySet().stream().map(e -> e.getKey() + " " + e.getValue()).collect(Collectors.joining(","));
        String[] Slots = availableSlots.split(",");

        String expectedResult = "2025-05-20 07:00-09:00";
        String actualResult = Slots[0];

        System.out.println("getAvailability() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToString() {
        Physiotherapist physio = getPhysiotherapist();

        String expectedResult = "Physiotherapist ID: 15, Name: Varun, Address: UK, Phone: 098765456789, Expertise: Physiotherapy, Treatments: [Neurological Physiotherapy], Availability: {2025-05-20=07:00-09:00, 2025-05-13=07:00-09:00, 2025-05-06=07:00-09:00, 2025-04-29=07:00-09:00}";
        String actualResult = physio.toString();

        System.out.println("testToString() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }
}