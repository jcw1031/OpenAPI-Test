package team.free.openapitest.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.free.openapitest.dto.StationLocation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "station")
@Entity
public class SubwayStation {

    @Id
    @Column(name = "station_id")
    private String id;

    @Column(name = "station_name", unique = true)
    private String name;

    @Column(name = "station_latitude")
    private String latitude;

    @Column(name = "station_longitude")
    private String longitude;

    @Column(name = "line_name")
    private String lineName;

    @Column(name = "station_address")
    private String address;

    @Builder
    public SubwayStation(String id, String name, String latitude, String longitude, String lineName, String address) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lineName = lineName;
        this.address = address;
    }

    public static SubwayStation of(String stationId, String stationName, String lineName,
                                   StationLocation stationLocation) {
        return SubwayStation.builder()
                .id(stationId)
                .name(stationName)
                .lineName(lineName)
                .latitude(stationLocation.getLatitude())
                .longitude(stationLocation.getLongitude())
                .address(stationLocation.getAddress())
                .build();
    }
}
