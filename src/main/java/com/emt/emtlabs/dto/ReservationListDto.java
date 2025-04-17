package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.model.domain.ReservationsList;

import java.util.List;

public record ReservationListDto(Long id, DisplayUserDto user, List<DisplayReservationDto> reservations) {

    public static ReservationListDto from(ReservationsList reservationList) {
        return new ReservationListDto(
                reservationList.getId(),
                DisplayUserDto.from(reservationList.getUser()),
                DisplayReservationDto.from(reservationList.getReservations()));
    }
}
