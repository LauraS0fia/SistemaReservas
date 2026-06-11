import { Routes } from '@angular/router';
import { ReservationCreateComponent } from './components/reservation-create/reservation-create.component';
import { ReservationListComponent } from './components/reservation-list/reservation-list.component';

export const routes: Routes = [
	{
		path: '',
		component: ReservationListComponent,
	},
	{
		path: 'reservations/new',
		component: ReservationCreateComponent,
	},
];
