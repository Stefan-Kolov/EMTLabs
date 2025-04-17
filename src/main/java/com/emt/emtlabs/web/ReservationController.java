package com.emt.emtlabs.web;

import com.emt.emtlabs.dto.CreateReservationDto;
import com.emt.emtlabs.dto.DisplayReservationDto;
import com.emt.emtlabs.model.domain.Reservation;
import com.emt.emtlabs.service.application.ReservationApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservations")
public class ReservationController {
    private final ReservationApplicationService reservationApplicationService;

    public ReservationController(ReservationApplicationService reservationApplicationService) {
        this.reservationApplicationService = reservationApplicationService;
    }

    @Operation(summary = "Get all reservations", description = "Retrieves a list of all available reservations.")
    @GetMapping
    public List<DisplayReservationDto> findAll(){
        return reservationApplicationService.getAllReservations();
    }

    @Operation(summary = "Get reservation by ID", description = "Finds a reservation by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayReservationDto> findById(@PathVariable Long id){
        DisplayReservationDto reservation = reservationApplicationService.getReservationById(id);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Add a new reservation", description = "Creates a new reservation.")
    @PostMapping("/add")
    public ResponseEntity<DisplayReservationDto> save(@RequestBody CreateReservationDto reservationDto) {
        DisplayReservationDto reservation = reservationApplicationService.addReservation(reservationDto);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update an existing reservation", description = "Updates a reservation by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayReservationDto> update(@PathVariable Long id, @RequestBody CreateReservationDto reservationDto) {
        DisplayReservationDto reservation = reservationApplicationService.editReservation(id,reservationDto);
        return reservation != null ? ResponseEntity.ok(reservation) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        DisplayReservationDto reservation = reservationApplicationService.getReservationById(id);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        reservationApplicationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    /*
    @GetMapping("/filter/{category}")
    public List<Reservation> findByCategory(@RequestParam(required = false) String category){
        if(category == null || category.isEmpty()){
            return reservationService.findAll();
        }
        return reservationService.findByCategory(category);
    }

    @PostMapping("/addreview")
    public ResponseEntity<Reservation> addReview(@RequestParam String description, @RequestParam Double rating, @RequestParam Long id) {
        return reservationService.addReview(description,rating,id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
*/

}
