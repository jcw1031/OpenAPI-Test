package team.free.openapitest.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenAPITest {

    public static final String SEOUL_OPEN_API_PREFIX = "http://openapi.seoul.go.kr:8088/";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${data.seoul.authentication_key}")
    private String authenticationKey;

    public ElevatorInfoDto getElevatorInfoDto() {
        String url = SEOUL_OPEN_API_PREFIX + authenticationKey + "/json/tbTraficElvtr/1/1000";

        return restTemplate.getForObject(url, ElevatorInfoDto.class);
    }
}
