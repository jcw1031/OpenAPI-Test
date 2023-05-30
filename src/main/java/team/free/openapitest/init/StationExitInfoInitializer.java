package team.free.openapitest.init;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import team.free.openapitest.util.GeographicalDistanceUtils;
import team.free.openapitest.util.KakaoAPIManager;

@Slf4j
@RequiredArgsConstructor
@Component
public class StationExitInfoInitializer {

    private final GeographicalDistanceUtils geographicalDistanceUtils;
    private final KakaoAPIManager kakaoAPIManager;

    public void initializeStationExitTable() {

    }
}
