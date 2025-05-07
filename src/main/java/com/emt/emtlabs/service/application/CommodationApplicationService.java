package com.emt.emtlabs.service.application;

import com.emt.emtlabs.dto.CreateCommodationDto;
import com.emt.emtlabs.dto.DisplayCommodationDto;

import java.util.List;

public interface CommodationApplicationService {
    List<DisplayCommodationDto> getAllReservations();
    DisplayCommodationDto getReservationById(Long id);
    DisplayCommodationDto addReservation(CreateCommodationDto createReservationDto);
    DisplayCommodationDto editReservation(Long id, CreateCommodationDto displayReservationDto);
    void deleteReservation(Long id);
}
