package team.free.openapitest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestTemplateTest {

    @Autowired
    OpenAPITest openAPITest;

    @DisplayName("RestTemplate 테스트")
    @Test
    void restTemplateTest() throws Exception {
        ElevatorInfoDto elevatorInfoDto = openAPITest.getElevatorInfoDto();
        ElevatorInfo elevatorInfo = elevatorInfoDto.getElevatorInfo();

        ResultInfo resultInfo = elevatorInfo.getResultInfo();
        String resultCode = resultInfo.getResultCode();
        System.out.println("resultCode = " + resultCode);

        Elevator elevator = elevatorInfo.getElevators().get(100);
        System.out.println("elevator = " + elevator);
    }
}
