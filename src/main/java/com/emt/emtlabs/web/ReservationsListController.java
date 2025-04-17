package com.emt.emtlabs.web;

import com.emt.emtlabs.dto.ReservationListDto;
import com.emt.emtlabs.model.domain.User;
import com.emt.emtlabs.service.application.ReservationListApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservationlist")
@Tag(name = "ReservationList API", description = "Endpoints for managing reservation list")
public class ReservationsListController {
    private final ReservationListApplicationService reservationListApplicationService;

    public ReservationsListController(ReservationListApplicationService reservationListApplicationService) {
        this.reservationListApplicationService = reservationListApplicationService;
    }

    @Operation(summary = "Get active reservations list", description = "Retrieves the authenticated user's current reservation list.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation list retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @GetMapping
    public ResponseEntity<ReservationListDto> getActiveReservationList(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return reservationListApplicationService.getActiveReservationList(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Add a reservation to reservation list", description = "Adds a reservation to the authenticated user's reservation list by reservation ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reservation added to reservation list successfully"),
            @ApiResponse(responseCode = "400", description = "Reservation not found or reserved"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @PostMapping("/add-reservation/{id}")
    public ResponseEntity<ReservationListDto> addBookToReservationList(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            return reservationListApplicationService.addReservationToReservationList(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Rent all reservations from reservation list", description = "Rents all available reservations from the authenticated user's reservation list.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "All reservations rented successfully"),
            @ApiResponse(responseCode = "400", description = "Reservation list is empty or reservations cannot be rented"),
            @ApiResponse(responseCode = "404", description = "Reservation list not found")
    })
    @PostMapping("/rent-all")
    public ResponseEntity<Void> rentAllReservationsFromReservationList(Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            reservationListApplicationService.rentAllReservationsFromReservationList(user.getUsername());
            return ResponseEntity.ok().build();
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
}
