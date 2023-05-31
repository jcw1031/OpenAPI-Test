package team.free.openapitest.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import team.free.openapitest.dto.Location;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

import static team.free.openapitest.init.SubwayStationInfoInitializer.LINE_ID_INDEX;
import static team.free.openapitest.init.SubwayStationInfoInitializer.LINE_NAME_INDEX;
import static team.free.openapitest.init.SubwayStationInfoInitializer.OPERATING_INSTITUTION_INDEX;
import static team.free.openapitest.init.SubwayStationInfoInitializer.STATION_ID_INDEX;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "station", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"station_latitude", "station_longitude"})
})
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

    @Column(name = "line_id")
    private String lineId;

    @Column(name = "line_name")
    private String lineName;

    @Column(name = "operating_institution")
    private String operatingInstitution;

    @Column(name = "station_address")
    private String address;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<StationExit> exits;

    @Builder
    public SubwayStation(String id, String name, String latitude, String longitude, String lineId, String lineName,
                         String operatingInstitution, String address) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lineId = lineId;
        this.lineName = lineName;
        this.operatingInstitution = operatingInstitution;
        this.address = address;
    }

    public static SubwayStation of(String stationName, Row row, Location location) {
        String stationId = row.getCell(STATION_ID_INDEX).toString();
        String lineId = row.getCell(LINE_ID_INDEX).toString();
        String lineName = row.getCell(LINE_NAME_INDEX).toString();
        String operatingInstitution = row.getCell(OPERATING_INSTITUTION_INDEX).toString();
        return SubwayStation.builder()
                .id(stationId)
                .name(stationName)
                .lineId(lineId)
                .lineName(lineName)
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .operatingInstitution(operatingInstitution)
                .address(location.getAddress())
                .build();
    }
}
