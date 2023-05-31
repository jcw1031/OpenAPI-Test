package team.free.openapitest.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import team.free.openapitest.domain.StationExit;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.dto.Location;
import team.free.openapitest.repository.StationExitRepository;
import team.free.openapitest.repository.SubwayStationRepository;
import team.free.openapitest.util.GeographicalDistanceUtils;
import team.free.openapitest.util.KakaoAPIManager;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class StationExitInfoInitializer {

    private final KakaoAPIManager kakaoAPIManager;
    private final SubwayStationRepository stationRepository;
    private final StationExitRepository exitRepository;

    public void initializeStationExitTable() {
        List<SubwayStation> stations = stationRepository.findAll();
        for (SubwayStation station : stations) {
            String stationName = station.getName();
            String lineName = station.getLineName();
            List<Location> exitLocationList = kakaoAPIManager.getExitLocationList(stationName, lineName);

            for (Location exitLocation : exitLocationList) {
                if (validDistance(station, exitLocation)) {
                    StationExit exit = StationExit.from(exitLocation);
                    exit.setStation(station);
                    log.info("exit = {}", exit);
                    exitRepository.save(exit);
                }
            }
        }
    }

    private boolean validDistance(SubwayStation station, Location exit) {
        double distance = GeographicalDistanceUtils.calculateDistance(
                Double.parseDouble(station.getLatitude()), Double.parseDouble(station.getLongitude()),
                Double.parseDouble(exit.getLatitude()), Double.parseDouble(exit.getLongitude())
        );

        return !(distance >= 500);
    }
}
