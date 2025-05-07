package com.emt.emtlabs.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodation_per_host")
@Immutable
public class AccomodationsPerHostView {
    @Id
    @Column(name="host_id")
    private Long hostId;

    @Column(name = "num_accommodations")
    private Integer numAccommodations;

}
