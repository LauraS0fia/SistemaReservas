package co.dev.laurapedraza.reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import co.dev.laurapedraza.reservation.dto.CreateReservationRequest;
import co.dev.laurapedraza.reservation.dto.ReservationResponse;
import co.dev.laurapedraza.reservation.entity.ReservationStatus;
import co.dev.laurapedraza.reservation.exception.ReservationAlreadyCancelledException;
import co.dev.laurapedraza.reservation.exception.ReservationConflictException;
import co.dev.laurapedraza.reservation.exception.ReservationNotFoundException;
import co.dev.laurapedraza.reservation.mapper.ReservationMapper;
import co.dev.laurapedraza.reservation.repository.ReservationRepository;

@Service
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final ReservationMapper reservationMapper;

	public ReservationService(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
		this.reservationRepository = reservationRepository;
		this.reservationMapper = reservationMapper;
	}

	/**
	 * Returns all reservations.
	 *
	 * @return all reservations
	 */
	@Transactional(readOnly = true)
	public List<ReservationResponse> findAll() {
		return reservationRepository.findAll().stream()
				.map(reservationMapper::toResponse)
				.toList();
	}

	/**
	 * Creates a new reservation after validating that the date and time slot is available.
	 *
	 * @param request the reservation data
	 * @return the created reservation
	 * @throws ReservationConflictException if an active reservation already exists for the same date and time
	 */
	@Transactional
	public ReservationResponse create(CreateReservationRequest request) {
		if (reservationRepository.existsByDateAndTimeAndStatus(
				request.date(), request.time(), ReservationStatus.ACTIVE)) {
			throw new ReservationConflictException(request.date(), request.time());
		}

		var entity = reservationMapper.toEntity(request);
		var saved = reservationRepository.save(entity);
		return reservationMapper.toResponse(saved);
	}

	/**
	 * Cancels an existing reservation by its identifier.
	 *
	 * @param id the reservation identifier
	 * @return the cancelled reservation
	 * @throws ReservationNotFoundException if no reservation exists with the given id
	 * @throws ReservationAlreadyCancelledException if the reservation is already cancelled
	 */
	@Transactional
	public ReservationResponse cancel(Long id) {
		var entity = reservationRepository.findById(id)
				.orElseThrow(() -> new ReservationNotFoundException(id));

		if (entity.getStatus() == ReservationStatus.CANCELLED) {
			throw new ReservationAlreadyCancelledException(id);
		}

		entity.setStatus(ReservationStatus.CANCELLED);
		var saved = reservationRepository.save(entity);
		return reservationMapper.toResponse(saved);
	}
}
