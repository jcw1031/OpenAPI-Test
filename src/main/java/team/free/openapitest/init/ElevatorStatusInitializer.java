package team.free.openapitest.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.free.openapitest.domain.Elevator;
import team.free.openapitest.domain.StationExit;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.dto.ElevatorStatus;
import team.free.openapitest.repository.ElevatorRepository;
import team.free.openapitest.repository.StationExitRepository;
import team.free.openapitest.repository.SubwayStationRepository;
import team.free.openapitest.util.SeoulOpenAPIManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class ElevatorStatusInitializer {

    private static final int STATION_NAME_INDEX = 1;
    private static final int ELEVATOR_FLOOR_INDEX = 3;
    private static final int ELEVATOR_LOCATION_INDEX = 4;
    private static final int ELEVATOR_STATUS_INDEX = 5;
    private static final int CATEGORY_INDEX = 6;

    private final SeoulOpenAPIManager seoulOpenAPIManager;
    private final SubwayStationRepository stationRepository;
    private final StationExitRepository exitRepository;
    private final ElevatorRepository elevatorRepository;
    private final Map<Long, String> nearestExit = new HashMap<>();

    public void initializeElevatorStatusMapping() {
        List<ElevatorStatus> elevatorStatusList = seoulOpenAPIManager.getElevatorStatus();
        List<SubwayStation> stations = stationRepository.findAll();
        for (SubwayStation station : stations) {
            List<Elevator> elevators = station.getElevators();
            List<StationExit> exits = station.getExits();
            setNearestExit(elevators, exits);
        }
        for (ElevatorStatus elevatorStatus : elevatorStatusList) {
            System.out.println("elevatorStatus = " + elevatorStatus);
        }
    }

    private void setNearestExit(List<Elevator> elevators, List<StationExit> exits) {

    }

    private static String getPureName(String stationName) {
        int index = stationName.indexOf("(");
        stationName = stationName.substring(0, index);
        return stationName;
    }
}
