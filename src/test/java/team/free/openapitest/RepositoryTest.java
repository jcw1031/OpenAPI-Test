package team.free.openapitest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import team.free.openapitest.domain.Elevator;
import team.free.openapitest.domain.StationExit;
import team.free.openapitest.domain.SubwayStation;
import team.free.openapitest.repository.SubwayStationRepository;

import java.util.List;

@Transactional
@SpringBootTest
public class RepositoryTest {

    @Autowired
    SubwayStationRepository stationRepository;

    @Test
    void test() throws Exception {
        List<SubwayStation> stations = stationRepository.findAll();
        SubwayStation station = stations.get(0);
        List<StationExit> exits = station.getExits();
        List<Elevator> elevators = station.getElevators();
        System.out.println("elevators.size() = " + elevators.size());
        System.out.println("exits.size() = " + exits.size());
    }
}
