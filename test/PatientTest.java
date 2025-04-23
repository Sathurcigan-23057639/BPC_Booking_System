import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient getPatient() {
        sampleData.initializeData();
        return BookingHandlingSystem.getInstance().getPatients().getFirst();
    }

    @Test
    void getId() {
        Patient patient = getPatient();

        int expectedResult = 1;
        int actualResult = Objects.requireNonNull(patient).getId();

        System.out.println("getID() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToString() {
        Patient patient = getPatient();

        String expectedResult = "Patient ID: 1, Name: Abi, Address: UK, Phone: 7654456789";
        String actualResult = Objects.requireNonNull(patient).toString();

        System.out.println("testToString() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }
}