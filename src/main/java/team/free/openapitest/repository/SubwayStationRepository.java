package team.free.openapitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.free.openapitest.domain.SubwayStation;

public interface SubwayStationRepository extends JpaRepository<SubwayStation, String> {
}
