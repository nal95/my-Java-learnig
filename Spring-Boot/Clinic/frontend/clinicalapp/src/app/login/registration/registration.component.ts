import { Component } from '@angular/core';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['../login.component.css']
})
export class RegistrationComponent {

  public titles = [
    {value: 'dr-0', viewValue: 'Dr'},
    {value: 'med-1', viewValue: 'Med'},
    {value: 'passiv-2', viewValue: 'Passiv'}
  ];


}
