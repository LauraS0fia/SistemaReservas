package co.dev.laurapedraza.reservation.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import co.dev.laurapedraza.reservation.entity.ReservationEntity;
import co.dev.laurapedraza.reservation.entity.ReservationStatus;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

	boolean existsByDateAndTime(LocalDate date, LocalTime time);

	boolean existsByDateAndTimeAndStatus(LocalDate date, LocalTime time, ReservationStatus status);
}
