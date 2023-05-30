package team.free.openapitest.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.repository.SubwayStationRepository;
import team.free.openapitest.util.GeographicalDistanceUtils;
import team.free.openapitest.util.KakaoAPIManager;
import team.free.openapitest.util.SeoulOpenAPIManager;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class StationExitInfoInitializer {

    private final GeographicalDistanceUtils geographicalDistanceUtils;
    private final KakaoAPIManager kakaoAPIManager;
    private final SeoulOpenAPIManager seoulOpenAPIManager;
    private final SubwayStationRepository stationRepository;

    public void initializeStationExitTable() {
        List<SubwayStation> stations = stationRepository.findAll();
        for (SubwayStation station : stations) {
            String stationName = station.getName();
            String lineName = station.getLineName();

        }
    }
}
