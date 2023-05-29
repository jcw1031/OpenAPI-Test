package team.free.openapitest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.free.openapitest.test.Elevator;
import team.free.openapitest.test.ElevatorInfo;
import team.free.openapitest.test.ElevatorInfoDto;
import team.free.openapitest.test.OpenAPITest;
import team.free.openapitest.test.ResultInfo;

import java.util.List;

@SpringBootTest
public class RestTemplateTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    OpenAPITest openAPITest;

    @DisplayName("RestTemplate 테스트")
    @Test
    void restTemplateTest() throws Exception {
        ElevatorInfoDto elevatorInfoDto = openAPITest.getElevatorInfoDto();
        ElevatorInfo elevatorInfo = elevatorInfoDto.getElevatorInfo();

        ResultInfo resultInfo = elevatorInfo.getResultInfo();
        String resultCode = resultInfo.getResultCode();
        log.info("resultCode = {}", resultCode);

        List<Elevator> elevators = elevatorInfo.getElevators();
        log.info("elevators.size() = {}", elevators.size());

        Elevator elevator = elevators.get(100);
        log.info("elevator = {}", elevator);
        String coordinates = elevator.getCoordinates();
        String pureCoordinates = coordinates.substring(6, coordinates.length() - 1);
        String longitude = pureCoordinates.split(" ")[0];
        String latitude = pureCoordinates.split(" ")[1];
        log.info("latitude: {}, longitude: {}", latitude, longitude);
    }
}
