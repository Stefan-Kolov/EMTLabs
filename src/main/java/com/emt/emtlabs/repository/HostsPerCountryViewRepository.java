package com.emt.emtlabs.repository;

import com.emt.emtlabs.model.views.HostsPerCountryView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HostsPerCountryViewRepository extends JpaRepository<HostsPerCountryView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.host_per_country", nativeQuery = true)
    void refreshMaterializedViews();
}
