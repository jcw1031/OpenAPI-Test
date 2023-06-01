package team.free.openapitest.init;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElevatorStatusInitializerTest {

    @Autowired
    ElevatorStatusInitializer elevatorStatusInitializer;

    @Test
    void test() throws Exception {
        elevatorStatusInitializer.initializeElevatorStatusMapping();
    }
}