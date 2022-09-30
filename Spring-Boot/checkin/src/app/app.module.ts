import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { ReservationInfosComponent } from './components/reservation-infos/reservation-infos.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ReservationComponent,
    ReservationInfosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
