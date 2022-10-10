import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {CheckinService} from "../../services/checkin.service";
import {of} from "rxjs";
import {Reservation} from "../../models/reservation";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  reservationId:Number|undefined;

  constructor(
    private  router:Router,
    private checkinService:CheckinService,
  ) { }

  ngOnInit(): void {
  }

  public onClick(){
    this.checkinService.getReservation(this.reservationId!).subscribe(
      res=> {
        this.checkinService.reservation = res;
        this.router.navigate(['checkIn']);
      }
    )
  }

}
