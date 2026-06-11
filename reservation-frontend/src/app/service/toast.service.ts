import { Injectable, signal } from '@angular/core';

export type ToastType = 'error' | 'success';

export interface Toast {
	id: string;
	message: string;
	type: ToastType;
}

@Injectable({
	providedIn: 'root',
})
export class ToastService {
	readonly toasts = signal<Toast[]>([]);

	/**
	 * Shows an error toast that auto-dismisses after a few seconds.
	 *
	 * @param message the error message to display
	 */
	showError(message: string): void {
		this.show(message, 'error');
	}

	/**
	 * Shows a success toast that auto-dismisses after a few seconds.
	 *
	 * @param message the success message to display
	 */
	showSuccess(message: string): void {
		this.show(message, 'success');
	}

	/**
	 * Removes a toast from the stack.
	 *
	 * @param id the toast identifier
	 */
	dismiss(id: string): void {
		this.toasts.update((toasts) => toasts.filter((toast) => toast.id !== id));
	}

	private show(message: string, type: ToastType): void {
		const toast: Toast = {
			id: crypto.randomUUID(),
			message,
			type,
		};

		this.toasts.update((toasts) => [...toasts, toast]);

		setTimeout(() => this.dismiss(toast.id), 5000);
	}
}
