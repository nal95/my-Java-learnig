import {Component, OnInit} from '@angular/core';
import {ClinicalHttpService} from "../../services/clinical-http.service";
import {ActivatedRoute} from "@angular/router";
import {Patient} from "../../model/data.model";

@Component({
  selector: 'app-analyze',
  templateUrl: './analyze.component.html',
  styleUrls: ['./analyze.component.css']
})
export class AnalyzeComponent implements OnInit {
  patientId!: number;
  patient!: Patient;

  constructor(private readonly httpService: ClinicalHttpService,
              private readonly _route: ActivatedRoute) { }

  ngOnInit(): void {
    this.patientId = +this._route.snapshot.params['id'];
    this.httpService.getPatientAnalyze(this.patientId).subscribe(
      (res)=>{
        console.log(res);
        this.patient = res;
      }
    );
  }

}
