package team.free.openapitest.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ElevatorLocationDto {

    @JsonAlias(value = "tbTraficElvtr")
    private ElevatorLocationRow elevatorLocationRow;
}
