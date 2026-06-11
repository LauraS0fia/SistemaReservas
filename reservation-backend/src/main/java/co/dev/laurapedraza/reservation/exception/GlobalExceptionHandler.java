package co.dev.laurapedraza.reservation.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Maps a not-found reservation to HTTP 404.
	 *
	 * @param ex the thrown exception
	 * @return problem detail response
	 */
	@ExceptionHandler(ReservationNotFoundException.class)
	public ProblemDetail handleNotFound(ReservationNotFoundException ex) {
		var problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
		problem.setTitle("Reservation Not Found");
		problem.setProperty("timestamp", Instant.now());
		return problem;
	}

	/**
	 * Maps a booking conflict to HTTP 409.
	 *
	 * @param ex the thrown exception
	 * @return problem detail response
	 */
	@ExceptionHandler(ReservationConflictException.class)
	public ProblemDetail handleConflict(ReservationConflictException ex) {
		var problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
		problem.setTitle("Reservation Conflict");
		problem.setProperty("timestamp", Instant.now());
		return problem;
	}

	/**
	 * Maps an already-cancelled reservation to HTTP 409.
	 *
	 * @param ex the thrown exception
	 * @return problem detail response
	 */
	@ExceptionHandler(ReservationAlreadyCancelledException.class)
	public ProblemDetail handleAlreadyCancelled(ReservationAlreadyCancelledException ex) {
		var problem = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
		problem.setTitle("Reservation Already Cancelled");
		problem.setProperty("timestamp", Instant.now());
		return problem;
	}

	/**
	 * Maps validation failures to HTTP 400.
	 *
	 * @param ex the thrown exception
	 * @return problem detail response
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
		var errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> "%s: %s".formatted(error.getField(), error.getDefaultMessage()))
				.toList();
		var problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
		problem.setTitle("Validation Error");
		problem.setProperty("errors", errors);
		problem.setProperty("timestamp", Instant.now());
		return problem;
	}
}
