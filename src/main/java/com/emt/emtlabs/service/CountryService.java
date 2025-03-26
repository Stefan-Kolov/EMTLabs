package com.emt.emtlabs.service;

import com.emt.emtlabs.model.Country;
import com.emt.emtlabs.model.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(CountryDto country);

    Optional<Country> update(CountryDto country,Long id);

    void deleteById(Long id);
}
