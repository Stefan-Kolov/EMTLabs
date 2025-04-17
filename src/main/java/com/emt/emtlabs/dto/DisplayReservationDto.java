package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.model.enumerations.Category;

import java.util.List;

public record DisplayReservationDto(Long id, String name, Category category, Long host, int numRooms, boolean reserved) {

    public static DisplayReservationDto from(Reservation reservation) {
        return new DisplayReservationDto(reservation.getId(), reservation.getName(),reservation.getCategory(),reservation.getHost().getId(),reservation.getNumRooms(), reservation.isReserved());
    }

    public static List<DisplayReservationDto> from(List<Reservation> reservations){
        return reservations.stream().map(DisplayReservationDto::from).toList();
    }
}
