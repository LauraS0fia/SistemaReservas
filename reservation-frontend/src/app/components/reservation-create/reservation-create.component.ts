import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ReservationFormComponent } from '../reservation-form/reservation-form.component';

@Component({
	selector: 'app-reservation-create',
	imports: [RouterLink, ReservationFormComponent],
	templateUrl: './reservation-create.component.html',
	styleUrl: './reservation-create.component.css',
})
export class ReservationCreateComponent {
	private readonly router = inject(Router);

	/**
	 * Navigates back to the reservation list after a successful creation.
	 */
	onReservationCreated(): void {
		this.router.navigate(['/']);
	}
}
