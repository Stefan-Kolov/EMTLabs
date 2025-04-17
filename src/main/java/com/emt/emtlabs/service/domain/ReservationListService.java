package com.emt.emtlabs.service.domain;

import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.model.domain.ReservationsList;

import java.util.List;

public interface ReservationListService {
    List<Reservation> listAllReservationsInReservationsList(Long reservationListId);
    ReservationsList getActiveReservationsList(String username);
    ReservationsList addReservationToReservationList(String username, Long reservationId);
    void rentAllReservations(String username);
}
