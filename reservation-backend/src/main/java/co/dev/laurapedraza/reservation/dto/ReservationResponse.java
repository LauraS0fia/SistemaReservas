package co.dev.laurapedraza.reservation.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import co.dev.laurapedraza.reservation.entity.ReservationStatus;

public record ReservationResponse(
		Long id,
		String clientName,
		LocalDate date,
		LocalTime time,
		String service,
		ReservationStatus status
) {
}
