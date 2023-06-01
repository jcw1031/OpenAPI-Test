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
    private static final String ELEVATOR_STATUS_REQUEST_ENDPOINT = "/SeoulMetroFaciInfo";
    private static final String ELEVATOR_STATUS_REQUEST_SIZE1 = "/1/1000";
    private static final String ELEVATOR_STATUS_REQUEST_SIZE2 = "/1001/2000";
    private static final String ELEVATOR_STATUS_REQUEST_SIZE3 = "/2001/3000";
    private static final String RESPONSE_TYPE = "/json";

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${data.seoul.authentication_key}")
    private String authenticationKey;

    public List<String[]> getStationElevatorLocation() {
        String responseBody = getResponseBody(ELEVATOR_LOCATION_REQUEST_ENDPOINT);

        return getElevatorArray(responseBody);
    }

    private List<String[]> getElevatorArray(String responseBody) {
        String[] elevatorsString = convertJsonToArray(responseBody);
        List<String[]> elevators = new ArrayList<>();
        for (String elevatorString : elevatorsString) {
            String[] elevator = elevatorString.replaceAll("\"", "").split(",");
            elevators.add(elevator);
        }

        return elevators;
    }

    public List<String[]> getElevatorStatus() {
        List<String[]> statusList = new ArrayList<>();
        String responseBody = getResponseBody(
                ELEVATOR_STATUS_REQUEST_ENDPOINT + ELEVATOR_STATUS_REQUEST_SIZE1);
        setElevatorStatusList(responseBody, statusList);
        responseBody = getResponseBody(
                ELEVATOR_STATUS_REQUEST_ENDPOINT + ELEVATOR_STATUS_REQUEST_SIZE2);
        setElevatorStatusList(responseBody, statusList);
        responseBody = getResponseBody(
                ELEVATOR_STATUS_REQUEST_ENDPOINT + ELEVATOR_STATUS_REQUEST_SIZE3);
        setElevatorStatusList(responseBody, statusList);

        return statusList;
    }

    private void setElevatorStatusList(String responseBody, List<String[]> statusList) {
        String[] statusListString = convertJsonToArray(responseBody);
        for (String statusString : statusListString) {
            String[] status = statusString.replaceAll("\"", "").split(",");
            if (status[6].split(":")[1].equals("EV") && status[2].contains("외부")) {
                statusList.add(status);
            }
        }
    }

    private String getResponseBody(String elevatorLocationRequestEndpoint) {
        String url = SEOUL_API_HOST + authenticationKey + RESPONSE_TYPE + elevatorLocationRequestEndpoint;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();
        return responseBody;
    }

    private String[] convertJsonToArray(String jsonString) {
        String row = jsonString.split("\"row\":\\[\\{")[1];
        String text = row.substring(0, row.length() - 4);
        return text.split("},\\{");
    }
}
