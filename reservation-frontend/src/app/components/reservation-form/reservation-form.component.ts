import { Component, inject, output, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
	FormBuilder,
	ReactiveFormsModule,
	Validators,
} from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { AVAILABLE_SERVICES } from '../../constants/available-services';
import { CreateReservationRequest } from '../../model/reservation.model';
import { ReservationService } from '../../service/reservation.service';
import { ToastService } from '../../service/toast.service';

@Component({
	selector: 'app-reservation-form',
	imports: [CommonModule, ReactiveFormsModule],
	templateUrl: './reservation-form.component.html',
	styleUrl: './reservation-form.component.css',
})
export class ReservationFormComponent {
	private readonly fb = inject(FormBuilder);
	private readonly reservationService = inject(ReservationService);
	private readonly toastService = inject(ToastService);

	readonly reservationCreated = output<void>();

	protected readonly services = AVAILABLE_SERVICES;
	protected readonly submitting = signal(false);

	protected readonly form = this.fb.nonNullable.group({
		nombreCliente: ['', Validators.required],
		fecha: ['', Validators.required],
		hora: ['', Validators.required],
		servicio: ['', Validators.required],
	});

	/**
	 * Validates the form and creates a new reservation.
	 */
	onSubmit(): void {
		if (this.form.invalid) {
			this.form.markAllAsTouched();
			return;
		}

		const request = this.toCreateRequest(this.form.getRawValue());
		this.submitting.set(true);

		this.reservationService.createReservation(request).subscribe({
			next: () => {
				this.submitting.set(false);
				this.form.reset();
				this.reservationCreated.emit();
			},
			error: (error: HttpErrorResponse) => {
				this.submitting.set(false);
				this.toastService.showError(this.extractErrorMessage(error));
			},
		});
	}

	/**
	 * Checks whether a field is invalid and has been touched.
	 *
	 * @param fieldName the form control name
	 * @returns true when the field should show a validation error
	 */
	isFieldInvalid(fieldName: keyof typeof this.form.controls): boolean {
		const control = this.form.controls[fieldName];
		return control.invalid && control.touched;
	}

	private toCreateRequest(formValue: {
		nombreCliente: string;
		fecha: string;
		hora: string;
		servicio: string;
	}): CreateReservationRequest {
		return {
			clientName: formValue.nombreCliente.trim(),
			date: formValue.fecha,
			time: formValue.hora,
			service: formValue.servicio,
		};
	}

	private extractErrorMessage(error: HttpErrorResponse): string {
		if (typeof error.error?.detail === 'string') {
			return error.error.detail;
		}

		return 'No se pudo guardar la reserva. Inténtalo de nuevo.';
	}
}
