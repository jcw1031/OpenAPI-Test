package team.free.openapitest.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.dto.Location;
import team.free.openapitest.repository.SubwayStationRepository;

import java.util.List;

@Transactional
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

    @Test
    void restTemplate() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://openapi.seoul.go.kr:8088/4d514267796a63773738686d796454/json/tbTraficElvtr/1/1000";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();

        String row = body.split("\"row\":\\[\\{")[1];
        String text = row.substring(0, row.length() - 4);

        String[] elevatorsString = text.split("},\\{");
        String[] elevator;
        for (String elevatorString : elevatorsString) {
            elevator = elevatorString.replaceAll("\"", "").split(",");
            String coordinate = elevator[1];
            System.out.println("coordinate = " + coordinate);
            String name = elevator[9];
            System.out.println("name = " + name);
        }

        /*List<SubwayStation> stations = stationRepository.findAll();
        for (SubwayStation station : stations) {
            String name = station.getName();
        }*/
    }
}