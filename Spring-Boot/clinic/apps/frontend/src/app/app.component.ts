import {Component, OnInit} from '@angular/core';
import {ClinicalHttpService} from "./clinic/services/clinical-http.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private readonly httpService: ClinicalHttpService,) {
  }

  title = 'clinic';

  ngOnInit() {
    this.httpService.getPatientsData().subscribe(
      res =>console.log("result:", res)
    );
  }

}
