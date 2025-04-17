package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.model.domain.Host;

import java.util.List;

public record DisplayHostDto(Long id, String name, String surname, Country country) {

    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(host.getId(), host.getName(), host.getSurname(), host.getCountry());
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).toList();
    }
}
