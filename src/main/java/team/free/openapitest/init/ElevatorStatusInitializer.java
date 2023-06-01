package team.free.openapitest.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.free.openapitest.repository.ElevatorRepository;
import team.free.openapitest.repository.StationExitRepository;
import team.free.openapitest.repository.SubwayStationRepository;
import team.free.openapitest.util.SeoulOpenAPIManager;

import java.util.List;

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

    public void initializeElevatorStatusMapping() {
        List<String[]> elevatorStatusList = seoulOpenAPIManager.getElevatorStatus();
        for (String[] elevatorStatus : elevatorStatusList) {
            System.out.println("elevatorStatus: stationName = " + elevatorStatus[1] + ", where=" + elevatorStatus[2]);
        }
    }
}
