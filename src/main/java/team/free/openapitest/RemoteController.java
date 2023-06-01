package team.free.openapitest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.free.openapitest.init.StationElevatorInfoInitializer;
import team.free.openapitest.init.StationExitInfoInitializer;
import team.free.openapitest.init.SubwayStationInfoInitializer;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class RemoteController {

    private final SubwayStationInfoInitializer subwayStationInfoInitializer;
    private final StationExitInfoInitializer stationExitInfoInitializer;
    private final StationElevatorInfoInitializer stationElevatorInfoInitializer;

    @GetMapping("/station-init")
    public void stationInitialize() throws IOException {
        subwayStationInfoInitializer.initializeSubwayStationTable();
    }

    @GetMapping("/exit-init")
    public void exitInitialize() throws IOException {
        stationExitInfoInitializer.initializeStationExitTable();
    }

    @GetMapping("/elevator-init")
    public void elevatorInitialize() throws IOException {
        long startTime = System.nanoTime();
        stationElevatorInfoInitializer.initializeElevatorLocationInfo();
        long endTime = System.nanoTime();
        System.out.println("Duration = " + (endTime - startTime));
    }

    @GetMapping("/contact-init")
    public void initializeStationContact() throws IOException {
        subwayStationInfoInitializer.setSubwayStationContact();
    }
}
