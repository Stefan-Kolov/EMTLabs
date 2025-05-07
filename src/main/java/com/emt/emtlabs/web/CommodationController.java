package com.emt.emtlabs.web;

import com.emt.emtlabs.dto.CreateCommodationDto;
import com.emt.emtlabs.dto.DisplayCommodationDto;
import com.emt.emtlabs.model.views.AccomodationsPerHostView;
import com.emt.emtlabs.service.application.CommodationApplicationService;
import com.emt.emtlabs.service.domain.CommodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping("/api/commodations")
@Tag(name = "Accommodations")
public class CommodationController {
    private final CommodationApplicationService commodationApplicationService;
    private final CommodationService commodationService;

    public CommodationController(CommodationApplicationService commodationApplicationService, CommodationService commodationService) {
        this.commodationApplicationService = commodationApplicationService;
        this.commodationService = commodationService;
    }

    @Operation(summary = "Get all accommodations", description = "Retrieves a list of all available accommodations.")
    @GetMapping
    public List<DisplayCommodationDto> findAll(){
        return commodationApplicationService.getAllReservations();
    }

    @Operation(summary = "Get accommodation by ID", description = "Finds a accommodation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCommodationDto> findById(@PathVariable Long id){
        DisplayCommodationDto accommodation = commodationApplicationService.getReservationById(id);
        return accommodation != null ? ResponseEntity.ok(accommodation) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new accommodation", description = "Creates a new accommodation.")
    @PostMapping("/add")
    public ResponseEntity<DisplayCommodationDto> save(@RequestBody CreateCommodationDto commodationDto) {
        DisplayCommodationDto accommodation = commodationApplicationService.addReservation(commodationDto);
        return accommodation != null ? ResponseEntity.ok(accommodation) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update an existing accommodation", description = "Updates a accommodation by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCommodationDto> update(@PathVariable Long id, @RequestBody CreateCommodationDto commodationDto) {
        DisplayCommodationDto accommodation = commodationApplicationService.editReservation(id,commodationDto);
        return accommodation != null ? ResponseEntity.ok(accommodation) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DisplayCommodationDto accommodation = commodationApplicationService.getReservationById(id);
        if (accommodation == null) {
            return ResponseEntity.notFound().build();
        }
        commodationApplicationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-host")
    public List<AccomodationsPerHostView> getAccommodationsByHost() {
        return commodationService.getAccommodationsPerHost();
    }
}
