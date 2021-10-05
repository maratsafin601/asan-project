package api.course.api.v1.models.unittests;

import api.course.api.v1.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// unit tests test method
public class UserUnitTests {

    @Test
    void equals_GivenIdenticalUsers_UsersAreEqual() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setFirstName("John");
        user2.setLastName("Doe");

        Assertions.assertTrue(user1.equals(user2));
    }
}
