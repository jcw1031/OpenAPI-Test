package team.free.openapitest.init;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StationElevatorInfoInitializerTest {

    @Autowired
    StationElevatorInfoInitializer stationElevatorInfoInitializer;

    @Test
    void getElevatorInfoTest() throws Exception {
        stationElevatorInfoInitializer.initializeElevatorLocationInfo();
    }

}