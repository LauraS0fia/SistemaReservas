package co.dev.laurapedraza.reservation.exception;

/**
 * Thrown when a reservation with the given identifier does not exist.
 */
public class ReservationNotFoundException extends RuntimeException {

	public ReservationNotFoundException(Long id) {
		super("Reservation not found with id: %d".formatted(id));
	}
}
