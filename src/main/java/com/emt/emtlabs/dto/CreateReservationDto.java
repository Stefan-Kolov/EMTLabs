package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.model.enumerations.Category;

public record CreateReservationDto (String name, Category category, Long host, Integer rooms) {



    public static CreateReservationDto from(Reservation reservation) {
        return new CreateReservationDto(
                reservation.getName(),
                reservation.getCategory(),
                reservation.getHost().getId(),
                reservation.getNumRooms()
        );
    }


    public Reservation toReservation(Host host) {
        return new Reservation(name,category,host,rooms);
    }
}
