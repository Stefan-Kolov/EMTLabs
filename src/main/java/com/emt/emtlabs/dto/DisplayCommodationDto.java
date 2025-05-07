package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Commodation;
import com.emt.emtlabs.model.enumerations.Category;

import java.util.List;

public record DisplayCommodationDto(Long id, String name, Category category, Long host, int numRooms, boolean reserved) {

    public static DisplayCommodationDto from(Commodation commodation) {
        return new DisplayCommodationDto(commodation.getId(), commodation.getName(), commodation.getCategory(), commodation.getHost().getId(), commodation.getNumRooms(), commodation.isReserved());
    }

    public static List<DisplayCommodationDto> from(List<Commodation> commodations){
        return commodations.stream().map(DisplayCommodationDto::from).toList();
    }
}
