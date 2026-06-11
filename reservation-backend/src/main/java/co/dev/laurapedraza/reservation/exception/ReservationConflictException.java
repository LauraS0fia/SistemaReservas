package co.dev.laurapedraza.reservation.exception;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Thrown when attempting to create a reservation for a date and time slot that is already booked.
 */
public class ReservationConflictException extends RuntimeException {

	public ReservationConflictException(LocalDate date, LocalTime time) {
		super("A reservation already exists for date %s and time %s".formatted(date, time));
	}
}
