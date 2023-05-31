package team.free.openapitest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "available")
    private boolean isAvailable;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private SubwayStation station;
}
