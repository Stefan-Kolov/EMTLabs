package com.emt.emtlabs.service.domain.impl;

import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.repository.CountryRepository;
import com.emt.emtlabs.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long country) {
        return countryRepository.findById(country).orElse(null);
    }

    @Override
    public Country addCountry(Country country) {
        Country newCountry = new Country();
        newCountry.setName(country.getName());
        newCountry.setContinent(country.getContinent());
        return countryRepository.save(newCountry);
    }

    @Override
    public Country editCountry(Long id, Country country) {
        Country oldCountry = countryRepository.findById(id).orElse(null);
        if (oldCountry == null) {
            return null;
        }
        oldCountry.setName(country.getName());
        oldCountry.setContinent(country.getContinent());
        return countryRepository.save(oldCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }
}
