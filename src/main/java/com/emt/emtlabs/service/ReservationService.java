package com.emt.emtlabs.service;

import com.emt.emtlabs.model.Category;
import com.emt.emtlabs.model.Reservation;
import com.emt.emtlabs.model.dto.ReservationDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> findAll();

    Optional<Reservation> findById(Long id);

    Optional<Reservation> save(ReservationDto reservationDto);

    Optional<Reservation> update(ReservationDto reservationDto,Long id);

    void deleteById(Long id);

    List<Reservation> findByCategory(String category);

    Optional<Reservation> addReview(String description, Double rating, Long reservationId);
}
