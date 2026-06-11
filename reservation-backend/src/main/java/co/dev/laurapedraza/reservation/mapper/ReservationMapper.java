package co.dev.laurapedraza.reservation.mapper;

import org.springframework.stereotype.Component;

import co.dev.laurapedraza.reservation.dto.CreateReservationRequest;
import co.dev.laurapedraza.reservation.dto.ReservationResponse;
import co.dev.laurapedraza.reservation.entity.ReservationEntity;
import co.dev.laurapedraza.reservation.entity.ReservationStatus;

@Component
public class ReservationMapper {

	/**
	 * Maps a create request to a new entity with active status.
	 *
	 * @param request the reservation data
	 * @return a new entity ready to persist
	 */
	public ReservationEntity toEntity(CreateReservationRequest request) {
		var entity = new ReservationEntity();
		entity.setClientName(request.clientName());
		entity.setDate(request.date());
		entity.setTime(request.time());
		entity.setService(request.service());
		entity.setStatus(ReservationStatus.ACTIVE);
		return entity;
	}

	/**
	 * Maps a persisted entity to a response DTO.
	 *
	 * @param entity the reservation entity
	 * @return the response DTO
	 */
	public ReservationResponse toResponse(ReservationEntity entity) {
		return new ReservationResponse(
				entity.getId(),
				entity.getClientName(),
				entity.getDate(),
				entity.getTime(),
				entity.getService(),
				entity.getStatus()
		);
	}
}
