import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { Reservation } from "../models/reservation"
import {Observable} from "rxjs";
import { Checkin } from "../models/checkin"

@Injectable({
  providedIn: 'root'
})
export class CheckinService {

  private reservationUrl:string = "http://localhost:8080/flightreservation/reservations/";
  public reservation!:Reservation;

  constructor( private httpClient:HttpClient) { }

  public getReservation(id:Number): Observable<Reservation>{
    return this.httpClient.get<Reservation>(this.reservationUrl+id);
  }

  public checkIn(requestBody:Checkin):Observable<number>{
    return this.httpClient.put<number>(this.reservationUrl,requestBody);
  }
}
