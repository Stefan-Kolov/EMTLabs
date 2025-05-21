package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Commodation;
import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.enumerations.Category;

public record CreateCommodationDto (String name, Category category, Long host, Integer numRooms) {



    public static CreateCommodationDto from(Commodation commodation) {
        return new CreateCommodationDto(
                commodation.getName(),
                commodation.getCategory(),
                commodation.getHost().getId(),
                commodation.getNumRooms()
        );
    }


    public Commodation toReservation(Host host) {
        return new Commodation(name,category,host,numRooms);
    }
}
