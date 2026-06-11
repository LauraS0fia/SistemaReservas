import { Component, inject, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import {
	ReservationResponse,
	ReservationStatus,
} from '../../model/reservation.model';
import { ReservationService } from '../../service/reservation.service';

@Component({
	selector: 'app-reservation-list',
	imports: [CommonModule, RouterLink],
	templateUrl: './reservation-list.component.html',
	styleUrl: './reservation-list.component.css',
})
export class ReservationListComponent implements OnInit {
	private readonly reservationService = inject(ReservationService);

	protected readonly reservations = signal<ReservationResponse[]>([]);
	protected readonly loading = signal(true);
	protected readonly error = signal<string | null>(null);
	protected readonly cancellingId = signal<number | null>(null);
	protected readonly ReservationStatus = ReservationStatus;

	ngOnInit(): void {
		this.loadReservations();
	}

	/**
	 * Loads all reservations from the API.
	 */
	loadReservations(): void {
		this.loading.set(true);
		this.error.set(null);

		this.reservationService.getAllReservations().subscribe({
			next: (reservations) => {
				this.reservations.set(reservations);
				this.loading.set(false);
			},
			error: () => {
				this.error.set('Could not load reservations. Please try again.');
				this.loading.set(false);
			},
		});
	}

	/**
	 * Cancels a reservation and updates the local list.
	 *
	 * @param id the reservation identifier
	 */
	cancelReservation(id: number): void {
		this.cancellingId.set(id);
		this.error.set(null);

		this.reservationService.cancelReservation(id).subscribe({
			next: (updated) => {
				this.reservations.update((list) =>
					list.map((reservation) =>
						reservation.id === id ? updated : reservation,
					),
				);
				this.cancellingId.set(null);
			},
			error: () => {
				this.error.set('Could not cancel the reservation. Please try again.');
				this.cancellingId.set(null);
			},
		});
	}

	/**
	 * Checks whether a reservation can be cancelled.
	 *
	 * @param reservation the reservation to evaluate
	 * @returns true when the reservation is active
	 */
	isCancellable(reservation: ReservationResponse): boolean {
		return reservation.status === ReservationStatus.ACTIVE;
	}
}
