package team.free.openapitest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenAPITest {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${data.seoul.authentication_key}")
    private String authenticationKey;

    public ElevatorInfoDto getElevatorInfoDto() {
        String url = "http://openapi.seoul.go.kr:8088/" + authenticationKey + "/json/tbTraficElvtr/1/1000";

        return restTemplate.getForObject(url, ElevatorInfoDto.class);
    }
}
