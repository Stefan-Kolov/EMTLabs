package com.emt.emtlabs.repository;

import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.projections.HostProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {

    @Query("select h.name as name, h.surname as surname from Host h")
    List<HostProjection> takeNameAndSurnameByProjection();
}
