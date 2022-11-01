import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { CommonModule } from "@angular/common";
import { MaterialModule } from "../material.module";
import { FormsModule } from "@angular/forms";


@NgModule({
  declarations: [
    HomeComponent
  ],

  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
  ],
})
export class HomeModule { }
