package team.free.openapitest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import team.free.openapitest.dto.StationExitLocationDto;

@Component
public class SeoulOpenAPIManager {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${data.seoul.authentication_key}")
    private String authenticationKey;

    public StationExitLocationDto getStationExitLocation() {
        return null;
    }
}
