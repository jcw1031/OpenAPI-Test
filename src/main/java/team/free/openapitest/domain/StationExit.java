package team.free.openapitest.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import team.free.openapitest.dto.Location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "station_exit", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"exit_latitude", "exit_longitude"})
})
@ToString
@Entity
public class StationExit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exit_id")
    private Long id;

    @Column(name = "exit_number")
    private String exitNumber;

    @Column(name = "exit_latitude")
    private String latitude;

    @Column(name = "exit_longitude")
    private String longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private SubwayStation station;

    @Builder
    public StationExit(String exitNumber, String latitude, String longitude) {
        this.exitNumber = exitNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static StationExit from(Location location) {
        String exitNumber = location.getName().split(" ")[2].split("번출구")[0];

        return StationExit.builder()
                .exitNumber(exitNumber)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }

    public void setStation(SubwayStation station) {
        this.station = station;
        station.getExits().add(this);
    }
}
