package com.emt.emtlabs.service.application;

import com.emt.emtlabs.dto.CreateCountryDto;
import com.emt.emtlabs.dto.DisplayCountryDto;

import java.util.List;

public interface CountryApplicationService {
    List<DisplayCountryDto> getAllCountries();
    DisplayCountryDto getCountryById(Long country);
    DisplayCountryDto addCountry(CreateCountryDto createCountryDto);
    DisplayCountryDto editCountry(Long id, CreateCountryDto createCountryDto);
    void deleteCountry(Long id);
}
