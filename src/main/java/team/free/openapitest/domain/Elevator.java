package team.free.openapitest.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "elevator", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"elevator_latitude", "elevator_longitude"})
})
@ToString
@Entity
public class Elevator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "elevator_id")
    private Long id;

    @Column(name = "elevator_latitude", nullable = false)
    private String latitude;

    @Column(name = "elevator_longitude", nullable = false)
    private String longitude;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "elevator_status")
    private ElevatorStatus status;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private SubwayStation station;

    @Builder
    public Elevator(String latitude, String longitude, ElevatorStatus status, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.description = description;
    }

    public void setStation(SubwayStation station) {
        this.station = station;
        station.getElevators().add(this);
    }
}
