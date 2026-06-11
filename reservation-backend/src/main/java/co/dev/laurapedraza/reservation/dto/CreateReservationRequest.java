package co.dev.laurapedraza.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateReservationRequest(
		@NotBlank String clientName,
		@NotNull LocalDate date,
		@NotNull LocalTime time,
		@NotBlank String service
) {
}
