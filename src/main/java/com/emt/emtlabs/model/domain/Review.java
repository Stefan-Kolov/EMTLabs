package com.emt.emtlabs.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Double rating;

    public Review(String description, Double rating) {
        this.description = description;
        this.rating = rating;
    }

    public Review() {

    }
}
