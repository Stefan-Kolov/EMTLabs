package com.emt.emtlabs.web;

import com.emt.emtlabs.dto.CreateCountryDto;
import com.emt.emtlabs.dto.DisplayCountryDto;
import com.emt.emtlabs.model.domain.Country;
import com.emt.emtlabs.service.application.CountryApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Countries")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @Operation(summary = "Get all countries", description = "Returns a list of all countries.")
    @GetMapping
    public List<DisplayCountryDto> findAll(){
        return countryApplicationService.getAllCountries();
    }

    @Operation(summary = "Get country by ID", description = "Finds a country by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id){
       DisplayCountryDto country = countryApplicationService.getCountryById(id);
       return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new country", description = "Creates a new country.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDto> addCountry(@RequestBody CreateCountryDto countryDto) {
        DisplayCountryDto country = countryApplicationService.addCountry(countryDto);
        return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update an existing country", description = "Updates a country by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDto> update(@PathVariable Long id, @RequestBody CreateCountryDto countryDto) {
        DisplayCountryDto country = countryApplicationService.editCountry(id, countryDto);
        return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DisplayCountryDto country = countryApplicationService.getCountryById(id);
        if(country == null) {
            return ResponseEntity.notFound().build();
        }
        countryApplicationService.deleteCountry(id);
        return ResponseEntity.ok().build();
    }



}
