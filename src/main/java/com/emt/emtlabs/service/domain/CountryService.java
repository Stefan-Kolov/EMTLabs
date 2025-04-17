package com.emt.emtlabs.service.domain;

import com.emt.emtlabs.model.domain.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAllCountries();
    Country getCountryById(Long country);
    Country addCountry(Country country);
    Country editCountry(Long id, Country country);

    void deleteCountry(Long id);
}
