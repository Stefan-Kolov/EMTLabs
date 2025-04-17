package com.emt.emtlabs.service.application.impl;


import com.emt.emtlabs.dto.CreateReservationDto;
import com.emt.emtlabs.dto.DisplayReservationDto;
import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.service.application.ReservationApplicationService;
import com.emt.emtlabs.service.domain.HostService;
import com.emt.emtlabs.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {
    private final ReservationService reservationService;
    private final HostService hostService;

    public ReservationApplicationServiceImpl(ReservationService reservationService, HostService hostService) {
        this.reservationService = reservationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayReservationDto> getAllReservations() {
        return DisplayReservationDto.from(reservationService.getAllReservation());
    }

    @Override
    public DisplayReservationDto getReservationById(Long id) {
        return DisplayReservationDto.from(reservationService.getReservationById(id));
    }

    @Override
    public DisplayReservationDto addReservation(CreateReservationDto createReservationDto) {
        Host host = hostService.getHostById(createReservationDto.host());
        if (host == null) {
            return null;
        }

        Reservation reservation = createReservationDto.toReservation(host);
        Reservation savedReservation = reservationService.addReservation(reservation);
        return DisplayReservationDto.from(savedReservation);
    }

    @Override
    public DisplayReservationDto editReservation(Long id, CreateReservationDto createReservationDto) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation == null) {
            return null;
        }
        Host host = hostService.getHostById(createReservationDto.host());
        if (host == null) {
            return null;
        }
        reservation.setName(createReservationDto.name());
        reservation.setCategory(createReservationDto.category());
        reservation.setHost(host);
        reservation.setNumRooms(createReservationDto.rooms());

        Reservation saved = reservationService.editReservation(id,reservation);
        return DisplayReservationDto.from(saved);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationService.deleteReservation(id);
    }
}
