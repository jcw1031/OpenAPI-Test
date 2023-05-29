package team.free.openapitest.test;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ElevatorInfo {

    @JsonAlias("RESULT")
    private ResultInfo resultInfo;
    @JsonAlias("row")
    private List<Elevator> elevators;
}
