package com.emt.emtlabs.service.domain;

import com.emt.emtlabs.model.domain.Commodation;
import com.emt.emtlabs.model.views.AccomodationsPerHostView;
import com.emt.emtlabs.repository.AccommodationsPerHostViewRepository;

import java.util.List;

public interface CommodationService {
    List<Commodation> getAllReservation();

    Commodation getReservationById(Long id);

    Commodation addReservation(Commodation commodation);

    Commodation editReservation(Long id, Commodation commodation);

    void deleteReservation(Long id);

    void refreshMaterializedView();

    List<AccomodationsPerHostView> getAccommodationsPerHost();
}
