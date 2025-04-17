package com.emt.emtlabs.repository;

import com.emt.emtlabs.model.domain.ReservationsList;
import com.emt.emtlabs.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationListRepository extends JpaRepository<ReservationsList, Long> {
    Optional<ReservationsList> findByUser(User user);
}
