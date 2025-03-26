package com.emt.emtlabs.model.dto;

import com.emt.emtlabs.model.Category;
import lombok.Data;

@Data
public class ReservationDto {

    private String name;

    private Category category;

    private Long host;

    private Integer numRooms;

    public ReservationDto(){

    }

    public ReservationDto(String name, Category category, Long host, int numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }
}
