package team.free.openapitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.free.openapitest.domain.Elevator;

public interface ElevatorRepository extends JpaRepository<Elevator, Long> {
}
