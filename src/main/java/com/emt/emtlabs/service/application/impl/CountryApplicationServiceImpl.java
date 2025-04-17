package com.emt.emtlabs.service.application.impl;

import com.emt.emtlabs.dto.CreateCountryDto;
import com.emt.emtlabs.dto.DisplayCountryDto;
import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.service.application.CountryApplicationService;
import com.emt.emtlabs.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> getAllCountries() {
        return DisplayCountryDto.from(countryService.getAllCountries());
    }

    @Override
    public DisplayCountryDto getCountryById(Long country) {
        Country c = countryService.getCountryById(country);
        return DisplayCountryDto.from(c);
    }

    @Override
    public DisplayCountryDto addCountry(CreateCountryDto createCountryDto) {
        Country country = new Country(createCountryDto.name(),createCountryDto.continent());
        Country saved = countryService.addCountry(country);
        return DisplayCountryDto.from(saved);
    }

    @Override
    public DisplayCountryDto editCountry(Long id, CreateCountryDto createCountryDto) {
        Country country = countryService.getCountryById(id);
        if(country == null) {
            return null;
        }
        country.setName(createCountryDto.name());
        country.setContinent(createCountryDto.continent());
        Country update = countryService.editCountry(id,country);
        return DisplayCountryDto.from(update);
    }

    @Override
    public void deleteCountry(Long id) {
        countryService.deleteCountry(id);
    }
}
