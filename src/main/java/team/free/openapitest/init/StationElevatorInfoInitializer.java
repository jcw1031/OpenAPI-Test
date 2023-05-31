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

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class StationElevatorInfoInitializer {

    private static final int ELEVATOR_COORDINATE_INDEX = 1;
    private static final int STATION_NAME_INDEX = 9;
    private static final int VALUE_INDEX = 1;
    private static final int LATITUDE_INDEX = 0;
    private static final int LONGITUDE_INDEX = 1;

    private final SeoulOpenAPIManager seoulOpenAPIManager;
    private final SubwayStationRepository stationRepository;
    private final ElevatorRepository elevatorRepository;

    public void initializeElevatorLocationInfo() {
        List<String[]> elevators = seoulOpenAPIManager.getStationElevatorLocation();
        List<SubwayStation> stations = stationRepository.findAll();
        for (SubwayStation station : stations) {
            mappingStationAndElevator(elevators, station);
        }
    }

    private void mappingStationAndElevator(List<String[]> elevators, SubwayStation station) {
        for (String[] elevatorInfo : elevators) {
            String[] stationName = elevatorInfo[STATION_NAME_INDEX].split(":");
            if (stationName.length == 1) {
                continue;
            }

            String name = stationName[VALUE_INDEX];
            String elevatorCoordinate = elevatorInfo[ELEVATOR_COORDINATE_INDEX].split(":")[VALUE_INDEX];
            String[] coordinate = getPureCoordinate(elevatorCoordinate);
            if (validStationName(station, name) && validDistance(station, coordinate)) {
                Elevator elevator = Elevator.builder()
                        .latitude(coordinate[LATITUDE_INDEX])
                        .longitude(coordinate[LONGITUDE_INDEX])
                        .isAvailable(true)
                        .build();
                elevator.setStation(station);
                elevatorRepository.save(elevator);
            }
        }
    }

    private String[] getPureCoordinate(String elevatorCoordinate) {
        String coordinate = elevatorCoordinate.substring(6, elevatorCoordinate.length() - 1);
        return coordinate.split(" ");
    }

    private boolean validStationName(SubwayStation station, String stationName) {
        if (stationName.isBlank() || !stationName.contains(station.getName())) {
            return true;
        }

        return false;
    }

    private boolean validDistance(SubwayStation station, String[] coordinate) {
        double distance = GeographicalDistanceUtils.calculateDistance(
                Double.parseDouble(station.getLatitude()), Double.parseDouble(station.getLongitude()),
                Double.parseDouble(coordinate[LATITUDE_INDEX]), Double.parseDouble(coordinate[LONGITUDE_INDEX])
        );

        return !(distance >= 500);
    }
}
