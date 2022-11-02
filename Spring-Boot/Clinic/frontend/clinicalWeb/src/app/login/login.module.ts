import {NgModule} from '@angular/core';
import {LoginComponent} from './login.component';
import {CommonModule} from "@angular/common";
import {MaterialModule} from "../material.module";
import {ReactiveFormsModule} from "@angular/forms";
import {RegistrationComponent} from './registration/registration.component';


@NgModule({
  declarations: [
    LoginComponent,
    RegistrationComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
  ]
})
export class LoginModule {
}
