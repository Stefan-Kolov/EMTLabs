package com.emt.emtlabs.service.impl;

import com.emt.emtlabs.model.Category;
import com.emt.emtlabs.model.Reservation;
import com.emt.emtlabs.model.Review;
import com.emt.emtlabs.model.dto.ReservationDto;
import com.emt.emtlabs.model.dto.ReviewDto;
import com.emt.emtlabs.repository.ReservationRepository;
import com.emt.emtlabs.service.HostService;
import com.emt.emtlabs.service.ReservationService;
import com.emt.emtlabs.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final HostService hostService;
    private final ReviewService reviewService;

    public ReservationServiceImpl(ReservationRepository reservationRepository, HostService hostService, ReviewService reviewService) {
        this.reservationRepository = reservationRepository;
        this.hostService = hostService;
        this.reviewService = reviewService;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Optional<Reservation> save(ReservationDto reservationDto) {
        if(reservationDto.getHost() != null && hostService.findById(reservationDto.getHost()).isPresent()) {
            return Optional.of(
                    reservationRepository.save(new Reservation(
                            reservationDto.getName(),
                            reservationDto.getCategory(),
                            hostService.findById(reservationDto.getHost()).get(),
                            reservationDto.getNumRooms())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> update(ReservationDto reservationDto, Long id) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    if(reservationDto.getName() != null) {
                        reservation.setName(reservationDto.getName());
                    }
                    if(reservationDto.getCategory() != null) {
                        reservation.setCategory(reservationDto.getCategory());
                    }
                    if(reservationDto.getNumRooms() != null) {
                        reservation.setNumRooms(reservationDto.getNumRooms());
                    }
                    if(reservationDto.getHost() != null && hostService.findById(reservationDto.getHost()).isPresent()) {
                        reservation.setHost(hostService.findById(reservationDto.getHost()).get());
                    }
                    return reservationRepository.save(reservation);
        });
    }

    @Override
    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> findByCategory(String category) {
        return reservationRepository.findAll().stream().filter(r -> r.getCategory().toString().equals(category)).toList();
    }

    @Override
    public Optional<Reservation> addReview(String description, Double rating, Long reservationId) {
        if(description != null && rating != null && reservationRepository.findById(reservationId).isPresent()) {
            Review review = new Review(description, rating);
            Reservation r = reservationRepository.findById(reservationId).get();
            r.getReviews().add(review);
            reviewService.save(review);
            return Optional.of(reservationRepository.save(r));
        }
        return Optional.empty();
    }


}
