package com.emt.emtlabs.dto;

import com.emt.emtlabs.model.domain.Country;

public record CreateCountryDto(String name, String continent) {

    public Country toCountry() {
        return new Country(name, continent);
    }
}
