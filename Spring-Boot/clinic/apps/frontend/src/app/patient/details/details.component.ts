import {Component, OnInit} from '@angular/core';
import {NewPatientData, Patient} from "../../model/data.model";
import {ClinicalHttpService} from "../../services/clinical-http.service";
import {ActivatedRoute} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {

  patient!: Patient;
  patientId!: number;
  categoryOptions: string [] = ['Heart rate', 'Blood pressure', 'Height/Weigh'];

  form: FormGroup = new FormGroup({
    category: new FormControl(''),
    value: new FormControl('')
  });

  constructor(private readonly httpService: ClinicalHttpService,
              private readonly _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.patientId = +this._route.snapshot.params['id'];
    this.httpService.getPatientData(this.patientId ).subscribe(
      (patient) => this.patient = patient
    );
  }

  savePatientData() {
    console.log(this.form.value);
    const data = {} as NewPatientData;
    data.patientId = String(this.patientId);
    data.componentName = this.form.controls['category'].value;
    if (this.form.controls['category'].value === 'Heart rate'){
      data.componentName = 'hr';
    }else if (this.form.controls['category'].value === 'Blood pressure'){
      data.componentName = 'bp';
    }else {
      data.componentName = 'hw';
    }
    data.componentValue = this.form.controls['value'].value;

    this.httpService.addPatientData(data).subscribe();
  }
}
