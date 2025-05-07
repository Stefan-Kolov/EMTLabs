package com.emt.emtlabs.model.domain;

import com.emt.emtlabs.model.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Commodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private int numRooms;

    private boolean reserved;

    public Commodation() {}

    public Commodation(String name, Category category, Host host, int numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        reserved = false;
    }

}
