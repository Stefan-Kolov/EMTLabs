package com.emt.emtlabs.service.application;

import com.emt.emtlabs.dto.CreateHostDto;
import com.emt.emtlabs.dto.DisplayHostDto;

import java.util.List;

public interface HostApplicationService {
    List<DisplayHostDto> getAllHosts();
    DisplayHostDto getHostById(Long id);
    DisplayHostDto addHost(CreateHostDto createHostDto);
    DisplayHostDto editHost(Long id, CreateHostDto createHostDto);
    void deleteHost(Long id);
}
