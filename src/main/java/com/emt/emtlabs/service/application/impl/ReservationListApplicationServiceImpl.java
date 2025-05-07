package com.emt.emtlabs.service.application.impl;

import com.emt.emtlabs.dto.DisplayCommodationDto;
import com.emt.emtlabs.dto.ReservationListDto;
import com.emt.emtlabs.service.application.ReservationListApplicationService;
import com.emt.emtlabs.service.domain.ReservationListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationListApplicationServiceImpl implements ReservationListApplicationService {
    private final ReservationListService reservationListService;

    public ReservationListApplicationServiceImpl(ReservationListService reservationListService) {
        this.reservationListService = reservationListService;
    }

    @Override
    public List<DisplayCommodationDto> listAllReservationsInReservationList(Long reservationListId) {
        return DisplayCommodationDto.from(reservationListService.listAllReservationsInReservationsList(reservationListId));
    }

    @Override
    public Optional<ReservationListDto> getActiveReservationList(String username) {
        return Optional.of(ReservationListDto.from(reservationListService.getActiveReservationsList(username)));
    }

    @Override
    public Optional<ReservationListDto> addReservationToReservationList(String username, Long reservationId) {
        return Optional.of(ReservationListDto.from(reservationListService.addReservationToReservationList(username, reservationId)));
    }

    @Override
    public void rentAllReservationsFromReservationList(String username) {
        reservationListService.rentAllReservations(username);
    }
}
