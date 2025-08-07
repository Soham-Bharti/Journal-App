package net.SohamDB.journalApp.service;

import net.SohamDB.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName(){
        assertNotNull(userRepository.findByUserName("shyam"));
    }

    @Disabled
    @Test
    public void testCheckAdminRole(){
        assertTrue(userRepository.findByUserName("soham").getRoles().contains("ADMIN"));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,4,5",
            "5,4,9",
            "2,3,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
