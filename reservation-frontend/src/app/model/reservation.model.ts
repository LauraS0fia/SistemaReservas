export interface CreateReservationRequest {
	clientName: string;
	date: string;
	time: string;
	service: string;
}

export enum ReservationStatus {
	ACTIVE = 'ACTIVE',
	CANCELLED = 'CANCELLED',
}

export interface ReservationResponse {
	id: number;
	clientName: string;
	date: string;
	time: string;
	service: string;
	status: ReservationStatus;
}