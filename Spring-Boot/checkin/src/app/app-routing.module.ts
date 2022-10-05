import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./components/home/home.component";
import {ReservationComponent} from "./components/reservation/reservation.component";
import {ReservationInfosComponent} from "./components/reservation-infos/reservation-infos.component";

const routes: Routes = [
  {
    path:'',
    redirectTo:'',
    pathMatch:'full'
  },
  {
    path:'startCheckIn',
    component: HomeComponent
  },
  {
    path:'checkIn',
    component: ReservationComponent
  },
  {
    path:'confirm',
    component: ReservationInfosComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
