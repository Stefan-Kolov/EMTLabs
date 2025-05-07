package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.Commodation;
import com.emt.emtlabs.model.domain.ReservationsList;
import com.emt.emtlabs.model.domain.User;
import com.emt.emtlabs.repository.ReservationListRepository;
import com.emt.emtlabs.service.domain.CommodationService;
import com.emt.emtlabs.service.domain.ReservationListService;
import com.emt.emtlabs.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationListServiceImpl implements ReservationListService {
    private final ReservationListRepository reservationListRepository;
    private final UserService userService;
    private final CommodationService commodationService;

    public ReservationListServiceImpl(ReservationListRepository reservationListRepository, UserService userService, CommodationService commodationService) {
        this.reservationListRepository = reservationListRepository;
        this.userService = userService;
        this.commodationService = commodationService;
    }

    @Override
    public List<Commodation> listAllReservationsInReservationsList(Long reservationListId) {
        ReservationsList reservationsList = reservationListRepository.findById(reservationListId).get();
        return reservationsList.getCommodations();
    }

    @Override
    public ReservationsList getActiveReservationsList(String username) {
        User user = userService.findByUsername(username);
        return reservationListRepository.findByUser(user).orElseGet(() -> reservationListRepository.save(new ReservationsList(user)));
    }

    @Override
    public ReservationsList addReservationToReservationList(String username, Long reservationId) {
        ReservationsList reservationsList = getActiveReservationsList(username);
        Commodation commodation = commodationService.getReservationById(reservationId);
        if(commodation == null) {
            throw  new IllegalArgumentException("Reservation not found");
        }
        if(commodation.isReserved()){
            throw  new IllegalArgumentException("Reservation is booked");
        }
        reservationsList.addReservation(commodation);
        return reservationListRepository.save(reservationsList);
    }

    @Override
    public void rentAllReservations(String username) {
        ReservationsList reservationsList = getActiveReservationsList(username);
        for(Commodation commodation : reservationsList.getCommodations()) {
            commodation.setReserved(true);
        }
        reservationsList.clear();
        reservationListRepository.save(reservationsList);
    }
}
