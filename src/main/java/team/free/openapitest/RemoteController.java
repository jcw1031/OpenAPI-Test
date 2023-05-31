package team.free.openapitest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import team.free.openapitest.init.StationExitInfoInitializer;

@RestController
public class RemoteController {

    private final StationExitInfoInitializer stationExitInfoInitializer;

    public RemoteController(StationExitInfoInitializer stationExitInfoInitializer) {
        this.stationExitInfoInitializer = stationExitInfoInitializer;
    }

    @GetMapping("/exit")
    public void exitInitialize() {
        stationExitInfoInitializer.initializeStationExitTable();
    }
}
