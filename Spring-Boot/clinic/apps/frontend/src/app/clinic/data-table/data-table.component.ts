import {Component, ViewChild} from '@angular/core';
import {ClinicalHttpService} from "../../services/clinical-http.service";
import {Patient} from "../../model/data.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {Router} from "@angular/router";

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css']
})
export class DataTableComponent {
  dataSource!: MatTableDataSource<Patient>;
  patients: Patient[] = []
  displayedColumns: string[] = ['id', 'firstname', 'lastname', 'age', 'addData', 'analyze'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private readonly httpService: ClinicalHttpService,
              private readonly router: Router) {
  }

  ngOnInit() {
    this.httpService.getPatientsData().subscribe(
      (res) => {
        this.patients = res;
        this.dataSource = new MatTableDataSource<Patient>(this.patients);
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  patientDetails(id: number) {
    this.router.navigate(['/patient-details',id]).then(() => {
      //Do Nothing})
    });
  }

  patientAnalyze(id: number) {
    this.router.navigate(['/patient-analyze',id]).then(() => {
      //Do Nothing})
    });
  }
}
