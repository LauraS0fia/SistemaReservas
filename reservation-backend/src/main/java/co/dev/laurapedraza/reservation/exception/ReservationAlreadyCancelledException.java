package co.dev.laurapedraza.reservation.exception;

/**
 * Thrown when attempting to cancel a reservation that is already cancelled.
 */
public class ReservationAlreadyCancelledException extends RuntimeException {

	public ReservationAlreadyCancelledException(Long id) {
		super("Reservation with id %d is already cancelled".formatted(id));
	}
}
