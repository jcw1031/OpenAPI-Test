package team.free.openapitest.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import team.free.openapitest.dto.ElevatorStatus;
import team.free.openapitest.dto.ElevatorStatusDto;

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

    public List<ElevatorStatus> getElevatorStatus() {
        List<ElevatorStatus> statusList = new ArrayList<>();
        List<ElevatorStatus> elevatorStatusList = getResponseBodyObject(
                ELEVATOR_STATUS_REQUEST_ENDPOINT + ELEVATOR_STATUS_REQUEST_SIZE1)
                .getElevatorStatusRow().getElevatorStatusList();
        setElevatorStatusList(elevatorStatusList, statusList);
        elevatorStatusList = getResponseBodyObject(
                ELEVATOR_STATUS_REQUEST_ENDPOINT + ELEVATOR_STATUS_REQUEST_SIZE2)
                .getElevatorStatusRow().getElevatorStatusList();
        setElevatorStatusList(elevatorStatusList, statusList);
        elevatorStatusList = getResponseBodyObject(
                ELEVATOR_STATUS_REQUEST_ENDPOINT + ELEVATOR_STATUS_REQUEST_SIZE3)
                .getElevatorStatusRow().getElevatorStatusList();
        setElevatorStatusList(elevatorStatusList, statusList);

        return statusList;
    }

    private void setElevatorStatusList(List<ElevatorStatus> elevatorStatusList, List<ElevatorStatus> statusList) {
        System.out.println("elevatorStatusList.size() = " + elevatorStatusList.size());
        for (ElevatorStatus elevatorStatus : elevatorStatusList) {
            if (elevatorStatus.getCategory().equals("EV")) {
                if (elevatorStatus.getElevatorName().contains("외부") || elevatorStatus.getLocation().contains("출")) {
                    statusList.add(elevatorStatus);
                }
            }
        }
    }

    private String getResponseBody(String endpoint) {
        String url = SEOUL_API_HOST + authenticationKey + RESPONSE_TYPE + endpoint;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String responseBody = responseEntity.getBody();
        return responseBody;
    }

    private ElevatorStatusDto getResponseBodyObject(String endpoint) {
        String url = SEOUL_API_HOST + authenticationKey + RESPONSE_TYPE + endpoint;
        return restTemplate.getForObject(url, ElevatorStatusDto.class);
    }

    private String[] convertJsonToArray(String jsonString) {
        String row = jsonString.split("\"row\":\\[\\{")[1];
        String text = row.substring(0, row.length() - 4);
        return text.split("},\\{");
    }
}
