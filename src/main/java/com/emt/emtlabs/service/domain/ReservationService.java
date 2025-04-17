package com.emt.emtlabs.service.domain;

import com.emt.emtlabs.model.domain.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservation();

    Reservation getReservationById(Long id);

    Reservation addReservation(Reservation reservation);

    Reservation editReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);



}
