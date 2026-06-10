package co.dev.laurapedraza.reservation.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import co.dev.laurapedraza.reservation.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    //Crea un repositorio Spring Data JPA para la entidad Reserva.
    //Debe extender JpaRepository y tener un método que permita verificar si ya existe una reserva para una fecha y una hora específicas.
    boolean existsByDateAndTime(LocalDate date, LocalTime time);
}
