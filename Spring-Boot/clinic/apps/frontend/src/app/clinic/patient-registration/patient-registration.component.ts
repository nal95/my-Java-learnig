import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ClinicalHttpService} from "../services/clinical-http.service";
import {NewPatient} from "../model/data.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-patient-registration',
  templateUrl: './patient-registration.component.html',
  styleUrls: ['./patient-registration.component.css']
})
export class PatientRegistrationComponent implements OnInit {

  form: FormGroup = new FormGroup({
    firstname: new FormControl(''),
    lastname: new FormControl(''),
    email: new FormControl('', [Validators.required, Validators.email]),
    age: new FormControl('', [Validators.min(1), Validators.maxLength(2)])
  });

  constructor(private readonly httpService: ClinicalHttpService,
              private readonly router: Router) {
  }

  ngOnInit(): void {
  }

  submit() {
    const patient = this.getPatientData();
    this.httpService.registerNewPatient(patient).subscribe(
      () => this.router.navigate(["/data-table"]).then(() => {
        //Do Nothing})
      })
    )
  }

  getErrorMessage() {
    if (this.form.controls['email'].hasError('required')) {
      return 'You must enter a value';
    }
    return this.form.controls['email'].hasError('email') ? 'Not a valid email' : '';
  }

  getPatientData(): NewPatient {
    const patient = {} as NewPatient;
    patient.firstName = this.form.controls['firstname'].value;
    patient.lastName = this.form.controls['lastname'].value;
    patient.age = this.form.controls['age'].value;
    patient.email = this.form.controls['email'].value;

    return patient;
  }
}
