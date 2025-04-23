import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getName() {
        User user = new User("Christopher", "UK", "23465643452223");

        String expectedResult = "Christopher";
        String actualResult = user.getName();

        System.out.println("getName() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getAddress() {
        User user = new User("Christopher", "UK", "23465643452223");

        String expectedResult = "UK";
        String actualResult = user.getAddress();

        System.out.println("getAddress() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setAddress() {
        User user = new User("Christopher", "UK", "23465643452223");
        user.setAddress("Sri lanka");

        String expectedResult = "Sri lanka";
        String actualResult = user.getAddress();

        System.out.println("setAddress() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getPhone() {
        User user = new User("Christopher", "UK", "23465643452223");

        String expectedResult = "23465643452223";
        String actualResult = user.getPhone();

        System.out.println("getPhone() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void setPhone() {
        User user = new User("Christopher", "UK", "23465643452223");
        user.setPhone("876567887998");

        String expectedResult = "876567887998";
        String actualResult = user.getPhone();

        System.out.println("setPhone() Function Testing\n-------------------------");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void isValidNumber() {
        boolean validNumber = User.isValidNumber("876544567890");
        boolean notValidNumber = User.isValidNumber("98765");

        System.out.println("isValidNumber() Function Testing Valid Number\n-------------------------");
        assertTrue(validNumber, "Test Passed");

        System.out.println("isValidNumber() Function Testing Not Valid Number\n-------------------------");
        assertFalse(notValidNumber, "Test Passed");
    }
}