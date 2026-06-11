import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ToastService } from '../../service/toast.service';

@Component({
	selector: 'app-toast',
	imports: [CommonModule],
	templateUrl: './toast.component.html',
	styleUrl: './toast.component.css',
})
export class ToastComponent {
	protected readonly toastService = inject(ToastService);
}
