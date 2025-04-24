import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentHandlingTest {

    @Test
    void convertDate() {
        String expectedResult = "Monday 28 April 2025";
        String actualResult = AppointmentHandling.convertDate("2025-04-28");

        System.out.println("convertDate() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }
}