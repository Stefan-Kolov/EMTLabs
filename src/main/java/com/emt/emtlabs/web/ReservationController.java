package com.emt.emtlabs.web;

import com.emt.emtlabs.model.Reservation;
import com.emt.emtlabs.model.dto.ReservationDto;
import com.emt.emtlabs.service.ReservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/swagger-ui/index.html#/

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> findAll(){
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id){
        return reservationService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Reservation> save(@RequestBody ReservationDto reservationDto) {
        return reservationService.save(reservationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Reservation> update(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        return reservationService.update(reservationDto, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (reservationService.findById(id).isPresent()) {
            reservationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

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



}
