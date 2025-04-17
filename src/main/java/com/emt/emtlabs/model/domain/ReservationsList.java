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
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @ManyToMany
    private List<Reservation> reservations = new ArrayList<>();

    public ReservationsList() {}

    public ReservationsList(User user) {
        this.user = user;
        reservations = new ArrayList<Reservation>();
    }

    public void addReservation(Reservation reservation) {
        if (!this.reservations.contains(reservation)) {  // Prevent duplicate reservations
            this.reservations.add(reservation);
        }
    }

    public void clear() {
        this.reservations.clear();
    }
}
