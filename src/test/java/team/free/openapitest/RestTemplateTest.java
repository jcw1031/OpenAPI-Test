package team.free.openapitest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RestTemplateTest {

    @Autowired
    OpenAPITest openAPITest;

    @DisplayName("RestTemplate 테스트")
    @Test
    void restTemplateTest() throws Exception {
        List<Elevator> elevators = openAPITest.getElevatorInfo();
        Elevator elevator = elevators.get(10);
        System.out.println("elevator = " + elevator);
    }
}
