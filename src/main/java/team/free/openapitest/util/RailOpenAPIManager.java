package team.free.openapitest.util;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RailOpenAPIManager {

    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
    }
}
