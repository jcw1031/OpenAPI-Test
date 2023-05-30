package team.free.openapitest.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.dto.Location;
import team.free.openapitest.repository.SubwayStationRepository;

import java.util.List;

@SpringBootTest
class KakaoAPIManagerTest {

    @Autowired
    KakaoAPIManager kakaoAPIManager;
    @Autowired
    SubwayStationRepository stationRepository;

    @Test
    void searchTest() throws Exception {
        List<SubwayStation> stations = stationRepository.findAll();

        for (SubwayStation station : stations) {
            List<Location> result = kakaoAPIManager.getExitLocationList(station.getName(), station.getLineName());
            System.out.println("result = " + result);
        }
    }
}