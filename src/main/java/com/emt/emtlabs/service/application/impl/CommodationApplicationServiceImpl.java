package com.emt.emtlabs.service.application.impl;


import com.emt.emtlabs.dto.CreateCommodationDto;
import com.emt.emtlabs.dto.DisplayCommodationDto;
import com.emt.emtlabs.model.domain.Commodation;
import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.service.application.CommodationApplicationService;
import com.emt.emtlabs.service.domain.HostService;
import com.emt.emtlabs.service.domain.CommodationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodationApplicationServiceImpl implements CommodationApplicationService {
    private final CommodationService commodationService;
    private final HostService hostService;

    public CommodationApplicationServiceImpl(CommodationService commodationService, HostService hostService) {
        this.commodationService = commodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayCommodationDto> getAllReservations() {
        return DisplayCommodationDto.from(commodationService.getAllReservation());
    }

    @Override
    public DisplayCommodationDto getReservationById(Long id) {
        return DisplayCommodationDto.from(commodationService.getReservationById(id));
    }

    @Override
    public DisplayCommodationDto addReservation(CreateCommodationDto createCommodationDto) {
        Host host = hostService.getHostById(createCommodationDto.host());
        if (host == null) {
            return null;
        }

        Commodation commodation = createCommodationDto.toReservation(host);
        Commodation savedCommodation = commodationService.addReservation(commodation);
        return DisplayCommodationDto.from(savedCommodation);
    }

    @Override
    public DisplayCommodationDto editReservation(Long id, CreateCommodationDto createCommodationDto) {
        Commodation commodation = commodationService.getReservationById(id);
        if (commodation == null) {
            return null;
        }
        Host host = hostService.getHostById(createCommodationDto.host());
        if (host == null) {
            return null;
        }
        commodation.setName(createCommodationDto.name());
        commodation.setCategory(createCommodationDto.category());
        commodation.setHost(host);
        commodation.setNumRooms(createCommodationDto.numRooms());

        Commodation saved = commodationService.editReservation(id, commodation);
        return DisplayCommodationDto.from(saved);
    }

    @Override
    public void deleteReservation(Long id) {
        commodationService.deleteReservation(id);
    }
}
