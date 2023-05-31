package team.free.openapitest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeoulOpenAPIManager {

    private static final String SEOUL_API_HOST = "http://openapi.seoul.go.kr:8088/";
    private static final String ELEVATOR_LOCATION_REQUEST_ENDPOINT = "/tbTraficElvtr/1/1000";
    private static final String RESPONSE_TYPE = "/json";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${data.seoul.authentication_key}")
    private String authenticationKey;

    public List<String[]> getStationElevatorLocation() {
        String url = SEOUL_API_HOST + authenticationKey + RESPONSE_TYPE + ELEVATOR_LOCATION_REQUEST_ENDPOINT;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();

        return getElevatorArray(responseBody);
    }

    private List<String[]> getElevatorArray(String responseBody) {
        String row = responseBody.split("\"row\":\\[\\{")[1];
        String text = row.substring(0, row.length() - 4);

        String[] elevatorsString = text.split("},\\{");
        List<String[]> elevators = new ArrayList<>();
        for (String elevatorString : elevatorsString) {
            String[] elevator = elevatorString.replaceAll("\"", "").split(",");
            elevators.add(elevator);
        }

        return elevators;
    }
}
