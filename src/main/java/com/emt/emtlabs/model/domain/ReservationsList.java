package com.emt.emtlabs.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ReservationsList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    private List<Commodation> commodations = new ArrayList<>();

    public ReservationsList() {}

    public ReservationsList(User user) {
        this.user = user;
        commodations = new ArrayList<Commodation>();
    }

    public void addReservation(Commodation commodation) {
        if (!this.commodations.contains(commodation)) {  // Prevent duplicate reservations
            this.commodations.add(commodation);
        }
    }

    public void clear() {
        this.commodations.clear();
    }
}
