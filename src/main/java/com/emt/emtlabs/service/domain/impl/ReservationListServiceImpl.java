package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.model.domain.ReservationsList;
import com.emt.emtlabs.model.domain.User;
import com.emt.emtlabs.repository.ReservationListRepository;
import com.emt.emtlabs.service.domain.ReservationListService;
import com.emt.emtlabs.service.domain.ReservationService;
import com.emt.emtlabs.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationListServiceImpl implements ReservationListService {
    private final ReservationListRepository reservationListRepository;
    private final UserService userService;
    private final ReservationService reservationService;

    public ReservationListServiceImpl(ReservationListRepository reservationListRepository, UserService userService, ReservationService reservationService) {
        this.reservationListRepository = reservationListRepository;
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @Override
    public List<Reservation> listAllReservationsInReservationsList(Long reservationListId) {
        ReservationsList reservationsList = reservationListRepository.findById(reservationListId).get();
        return reservationsList.getReservations();
    }

    @Override
    public ReservationsList getActiveReservationsList(String username) {
        User user = userService.findByUsername(username);
        return reservationListRepository.findByUser(user).orElseGet(() -> reservationListRepository.save(new ReservationsList(user)));
    }

    @Override
    public ReservationsList addReservationToReservationList(String username, Long reservationId) {
        ReservationsList reservationsList = getActiveReservationsList(username);
        Reservation reservation = reservationService.getReservationById(reservationId);
        if(reservation == null) {
            throw  new IllegalArgumentException("Reservation not found");
        }
        if(reservation.isReserved()){
            throw  new IllegalArgumentException("Reservation is booked");
        }
        reservationsList.addReservation(reservation);
        return reservationListRepository.save(reservationsList);
    }

    @Override
    public void rentAllReservations(String username) {
        ReservationsList reservationsList = getActiveReservationsList(username);
        for(Reservation reservation : reservationsList.getReservations()) {
            reservation.setReserved(true);
        }
        reservationsList.clear();
        reservationListRepository.save(reservationsList);
    }
}
