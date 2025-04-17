package com.emt.emtlabs.service.application;

import com.emt.emtlabs.dto.CreateReservationDto;
import com.emt.emtlabs.dto.DisplayReservationDto;

import java.util.List;

public interface ReservationApplicationService {
    List<DisplayReservationDto> getAllReservations();
    DisplayReservationDto getReservationById(Long id);
    DisplayReservationDto addReservation(CreateReservationDto createReservationDto);
    DisplayReservationDto editReservation(Long id, CreateReservationDto displayReservationDto);
    void deleteReservation(Long id);
}
