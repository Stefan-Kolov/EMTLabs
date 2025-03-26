package com.emt.emtlabs.service.impl;

import com.emt.emtlabs.model.Country;
import com.emt.emtlabs.model.Reservation;
import com.emt.emtlabs.model.dto.CountryDto;
import com.emt.emtlabs.repository.CountryRepository;
import com.emt.emtlabs.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto country) {
        if(country.getName() != null && country.getContinent() != null) {
            return Optional.of(
                    countryRepository.save(new Country(country.getName(), country.getContinent())));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Country> update(CountryDto country, Long id) {
        return countryRepository.findById(id).map(existingCountry -> {
            if(country.getName() != null){
                existingCountry.setName(country.getName());
            }
            if(country.getContinent() != null){
                existingCountry.setContinent(country.getContinent());
            }
            return countryRepository.save(existingCountry);
        });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
