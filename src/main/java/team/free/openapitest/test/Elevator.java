package team.free.openapitest.test;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Elevator {

    @JsonAlias("NODE_ID")
    private Double id;
    @JsonAlias("TYPE")
    private String type;
    @JsonAlias("NODE_WKT")
    private String coordinates;
    @JsonAlias("SW_CD")
    private String stationCode;
    @JsonAlias("SW_NM")
    private String stationName;
}
