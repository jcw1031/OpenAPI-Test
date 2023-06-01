package team.free.openapitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.free.openapitest.domain.SubwayStation;

import java.util.Optional;

public interface SubwayStationRepository extends JpaRepository<SubwayStation, String> {

    Optional<SubwayStation> findByName(String name);
}
