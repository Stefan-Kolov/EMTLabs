package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.repository.ReservationRepository;
import com.emt.emtlabs.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation editReservation(Long id, Reservation reservation) {
        Reservation result = reservationRepository.findById(id).orElse(null);
        if(result == null){
            return null;
        }
        result.setName(reservation.getName());
        result.setCategory(reservation.getCategory());
        result.setHost(reservation.getHost());
        result.setNumRooms(reservation.getNumRooms());
        return reservationRepository.save(result);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
