package team.free.openapitest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OpenAPITest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${data.seoul.authentication_key}")
    private String authenticationKey;

    public List<Elevator> getElevatorInfo() {
        String url = "http://openapi.seoul.go.kr:8088/" + authenticationKey + "/json/tbTraficElvtr/1/1000";

        ResponseEntity<ElevatorInfoDto> elevatorInfoEntity = restTemplate.getForEntity(url, ElevatorInfoDto.class);
        ElevatorInfoDto body = elevatorInfoEntity.getBody();

        return body.getElevatorInfo().getElevators();
    }
}
