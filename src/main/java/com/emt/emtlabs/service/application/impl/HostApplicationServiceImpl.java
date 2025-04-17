package com.emt.emtlabs.service.application.impl;

import com.emt.emtlabs.dto.CreateHostDto;
import com.emt.emtlabs.dto.DisplayHostDto;
import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.model.domain.Host;
import com.emt.emtlabs.service.application.HostApplicationService;
import com.emt.emtlabs.service.domain.CountryService;
import com.emt.emtlabs.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> getAllHosts() {
        return DisplayHostDto.from(hostService.getAllHosts());
    }

    @Override
    public DisplayHostDto getHostById(Long id) {
        Host host = hostService.getHostById(id);
        if (host == null) {
            return null;
        }
        return DisplayHostDto.from(host);
    }

    @Override
    public DisplayHostDto addHost(CreateHostDto createHostDto) {
        Country c = countryService.getCountryById(createHostDto.country());
        if (c == null) {
            return null;
        }
        Host host = createHostDto.toHost(c);
        Host saved = hostService.addHost(host);
        return DisplayHostDto.from(saved);
    }

    @Override
    public DisplayHostDto editHost(Long id, CreateHostDto createHostDto) {
        Host existingHost = hostService.getHostById(id);
        if (existingHost == null) {
            return null;
        }
        Country c = countryService.getCountryById(createHostDto.country());
        if (c == null) {
            return null;
        }
        existingHost.setName(createHostDto.name());
        existingHost.setSurname(createHostDto.surname());
        existingHost.setCountry(c);

        Host updated = hostService.editHost(id, existingHost);
        return DisplayHostDto.from(updated);
    }

    @Override
    public void deleteHost(Long id) {
        hostService.deleteHost(id);
    }
}
