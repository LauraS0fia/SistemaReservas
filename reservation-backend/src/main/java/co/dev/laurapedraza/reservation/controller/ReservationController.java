package co.dev.laurapedraza.reservation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.dev.laurapedraza.reservation.dto.CreateReservationRequest;
import co.dev.laurapedraza.reservation.dto.ReservationResponse;
import co.dev.laurapedraza.reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/reservations")
@Tag(name = "Reservations", description = "Reservation management endpoints")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	/**
	 * Lists all reservations.
	 *
	 * @return all reservations
	 */
	@GetMapping
	@Operation(summary = "List all reservations")
	public List<ReservationResponse> findAll() {
		return reservationService.findAll();
	}

	/**
	 * Creates a new reservation.
	 *
	 * @param request the reservation data
	 * @return the created reservation
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a new reservation")
	public ReservationResponse create(@Valid @RequestBody CreateReservationRequest request) {
		return reservationService.create(request);
	}

	/**
	 * Cancels an existing reservation.
	 *
	 * @param id the reservation identifier
	 * @return the cancelled reservation
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "Cancel a reservation")
	public ReservationResponse cancel(@PathVariable Long id) {
		return reservationService.cancel(id);
	}
}
