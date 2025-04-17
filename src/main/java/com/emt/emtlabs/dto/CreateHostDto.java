package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.model.domain.Host;

public record CreateHostDto (String name, String surname, Long country) {

    public Host toHost(Country country){
        return new Host(name,surname,country);
    }
}
