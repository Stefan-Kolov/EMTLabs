package com.emt.emtlabs.model.domain;

import com.emt.emtlabs.model.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Category category;

    @ManyToOne
    private Host host;

    private int numRooms;

    @OneToMany
    private List<Review> reviews;

    private boolean reserved;

    public Reservation() {}

    public Reservation(String name, Category category, Host host, int numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        reserved = false;
    }

}
