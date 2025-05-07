package com.emt.emtlabs.repository;

import com.emt.emtlabs.model.views.AccomodationsPerHostView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccommodationsPerHostViewRepository extends JpaRepository<AccomodationsPerHostView,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.accommodation_per_host", nativeQuery = true)
    void refreshMaterializedViews();
}
