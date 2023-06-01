package team.free.openapitest.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.free.openapitest.domain.Elevator;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.repository.ElevatorRepository;
import team.free.openapitest.repository.SubwayStationRepository;
import team.free.openapitest.util.GeographicalDistanceUtils;
import team.free.openapitest.util.SeoulOpenAPIManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class StationElevatorInfoInitializer {

    private static final int ELEVATOR_COORDINATE_INDEX = 1;
    private static final int STATION_NAME_INDEX = 9;
    private static final int VALUE_INDEX = 1;
    private static final int LATITUDE_INDEX = 1;
    private static final int LONGITUDE_INDEX = 0;

    private final SeoulOpenAPIManager seoulOpenAPIManager;
    private final SubwayStationRepository stationRepository;
    private final ElevatorRepository elevatorRepository;
    private final Map<String, List<String[]>> elevatorMap = new HashMap<>();


    public void initializeElevatorLocationInfo() {
        List<String[]> elevators = seoulOpenAPIManager.getStationElevatorLocation();
        setElevatorMap(elevators);
        List<SubwayStation> stations = stationRepository.findAll();
        for (SubwayStation station : stations) {
            mappingStationAndElevator(station);
        }
    }

    private void setElevatorMap(List<String[]> elevators) {
        for (String[] elevator : elevators) {
            String[] stationName = elevator[STATION_NAME_INDEX].split(":");
            if (stationName.length == 1) {
                continue;
            }

            String name = stationName[VALUE_INDEX];
            if (name.contains("(")) {
                name = getPureName(name);
                if (name.isBlank()) {
                    continue;
                }
            }

            List<String[]> elevatorValue = elevatorMap.get(name);
            if (elevatorValue == null) {
                elevatorValue = new ArrayList<>();
                elevatorValue.add(elevator);
                elevatorMap.put(name, elevatorValue);
                continue;
            }

            elevatorValue.add(elevator);
            elevatorMap.put(name, elevatorValue);
        }

        System.out.println("elevatorMap = " + elevatorMap);
    }

    private void mappingStationAndElevator(SubwayStation station) {
        String stationName = station.getName();
        List<String[]> elevators = elevatorMap.get(stationName);
        if (elevators == null) {
            return;
        }

        for (String[] elevatorInfo : elevators) {
            String elevatorCoordinate = elevatorInfo[ELEVATOR_COORDINATE_INDEX].split(":")[VALUE_INDEX];
            String[] coordinate = getPureCoordinate(elevatorCoordinate);
            if (validDistance(station, coordinate)) {
                Elevator elevator = Elevator.builder()
                        .latitude(coordinate[LATITUDE_INDEX])
                        .longitude(coordinate[LONGITUDE_INDEX])
                        .build();
                elevator.setStation(station);
                elevatorRepository.save(elevator);
            }
        }
    }

    private static String getPureName(String stationName) {
        int index = stationName.indexOf("(");
        stationName = stationName.substring(0, index);
        return stationName;
    }

    private String[] getPureCoordinate(String elevatorCoordinate) {
        String coordinate = elevatorCoordinate.substring(6, elevatorCoordinate.length() - 1);
        return coordinate.split(" ");
    }

    private boolean validDistance(SubwayStation station, String[] coordinate) {
        double distance = GeographicalDistanceUtils.calculateDistance(
                Double.parseDouble(station.getLatitude()), Double.parseDouble(station.getLongitude()),
                Double.parseDouble(coordinate[LATITUDE_INDEX]), Double.parseDouble(coordinate[LONGITUDE_INDEX])
        );

        return (distance < 500);
    }
}
