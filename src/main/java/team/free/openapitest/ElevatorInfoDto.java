package team.free.openapitest;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ElevatorInfoDto {

    @JsonAlias("tbTraficElvtr")
    private ElevatorInfo elevatorInfo;
}
