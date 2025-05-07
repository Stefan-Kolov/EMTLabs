package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.Commodation;
import com.emt.emtlabs.model.views.AccomodationsPerHostView;
import com.emt.emtlabs.repository.AccommodationsPerHostViewRepository;
import com.emt.emtlabs.repository.CommodationRepository;
import com.emt.emtlabs.service.domain.CommodationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodationServiceImpl implements CommodationService {
    private final CommodationRepository reservationRepository;
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    public CommodationServiceImpl(CommodationRepository reservationRepository, AccommodationsPerHostViewRepository accommodationsPerHostViewRepository) {
        this.reservationRepository = reservationRepository;
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
    }

    @Override
    public List<Commodation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Commodation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Commodation addReservation(Commodation commodation) {
        return reservationRepository.save(commodation);
    }

    @Override
    public Commodation editReservation(Long id, Commodation commodation) {
        Commodation result = reservationRepository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        result.setName(commodation.getName());
        result.setCategory(commodation.getCategory());
        result.setHost(commodation.getHost());
        result.setNumRooms(commodation.getNumRooms());
        return reservationRepository.save(result);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
        accommodationsPerHostViewRepository.refreshMaterializedViews();
    }

    @Override
    public List<AccomodationsPerHostView> getAccommodationsPerHost() {
        return accommodationsPerHostViewRepository.findAll();
    }
}
