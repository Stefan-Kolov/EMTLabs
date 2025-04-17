package com.emt.emtlabs.service.application;

import com.emt.emtlabs.dto.DisplayReservationDto;
import com.emt.emtlabs.dto.ReservationListDto;

import java.util.List;
import java.util.Optional;

public interface ReservationListApplicationService {
    List<DisplayReservationDto> listAllReservationsInReservationList(Long reservationListId);

    Optional<ReservationListDto> getActiveReservationList(String username);

    Optional<ReservationListDto> addReservationToReservationList(String username, Long reservationId);

    void rentAllReservationsFromReservationList(String username);
}
