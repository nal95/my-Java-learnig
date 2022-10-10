import { Component, OnInit, Input } from '@angular/core';
import {Router} from "@angular/router";
import {CheckinService} from "../../services/checkin.service";
import {Reservation} from "../../models/reservation";


@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  public reservation!:Reservation;
  constructor(
    private  router:Router,
    private checkinService:CheckinService,
  ) { }

  ngOnInit(): void {
    this.reservation = this.checkinService.reservation;
  }


}
