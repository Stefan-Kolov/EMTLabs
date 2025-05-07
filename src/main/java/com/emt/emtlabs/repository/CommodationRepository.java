package com.emt.emtlabs.repository;

import com.emt.emtlabs.model.domain.Commodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodationRepository extends JpaRepository<Commodation, Long> {
}
