package team.free.openapitest.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ElevatorStatus {

    @JsonAlias(value = "STATION_NM")
    private String stationName;

    @JsonAlias(value = "FACI_NM")
    private String elevatorName;

    @JsonAlias(value = "STUP_LCTN")
    private String elevatorFloor;

    @JsonAlias(value = "LOCATION")
    private String location;

    @JsonAlias(value = "USE_YN")
    private String status;

    @JsonAlias(value = "GUBUN")
    private String category;

}
