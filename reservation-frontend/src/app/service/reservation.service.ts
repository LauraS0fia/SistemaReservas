import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { CreateReservationRequest, ReservationResponse } from "../model/reservation.model";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class ReservationService {
    private http = inject(HttpClient);
    private baseUrl = `${environment.apiUrl}/api/v1/reservations`;
  
    getAllReservations(): Observable<ReservationResponse[]> {
        return this.http.get<ReservationResponse[]>(`${this.baseUrl}`);
    }
    
    createReservation(request: CreateReservationRequest): Observable<ReservationResponse> {
        return this.http.post<ReservationResponse>(`${this.baseUrl}`, request);
    }

    cancelReservation(id: number): Observable<ReservationResponse> {
        return this.http.delete<ReservationResponse>(`${this.baseUrl}/${id}`);
    }
    
}