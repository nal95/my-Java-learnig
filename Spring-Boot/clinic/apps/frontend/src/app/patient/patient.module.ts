import {NgModule} from '@angular/core';
import {MaterialModule} from "../material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RegistrationComponent} from "./registration/registration.component";
import { DetailsComponent } from './details/details.component';
import {CommonModule} from "@angular/common";
import { AnalyzeComponent } from './analyze/analyze.component';


@NgModule({
  declarations: [
    RegistrationComponent,
    DetailsComponent,
    AnalyzeComponent
  ],
  exports: [
    RegistrationComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class PatientModule {
}
