package com.emt.emtlabs.model.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private String description;
    private Double rating;
    private Long id;

    public ReviewDto(String description, Double rating, Long id) {
        this.description = description;
        this.rating = rating;
        this.id = id;
    }

    public ReviewDto() {}
}
