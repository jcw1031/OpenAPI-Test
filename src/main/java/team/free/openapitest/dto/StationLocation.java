package team.free.openapitest.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StationLocation {

    @JsonAlias("place_name")
    private String name;
    @JsonAlias("y")
    private String latitude;
    @JsonAlias("x")
    private String longitude;
    @JsonAlias("address_name")
    private String address;
}
