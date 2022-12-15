import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {NewPatient, NewPatientResponse, Patient} from "../model/data.model";
import {PATIENTS} from "../../clinic-backend-routes";

@Injectable({
  providedIn: 'root'
})
export class ClinicalHttpService {

  constructor(private readonly httpClient: HttpClient) {
  }


  public getPatientsData(): Observable<Patient []> {
    return this.httpClient.get<Patient []>(PATIENTS).pipe(
      tap(() => console.log('Http GET ALL Patient data...'))
    );
  }

  public registerNewPatient(patient: NewPatient): Observable<NewPatientResponse> {
    return this.httpClient.post<NewPatientResponse>((PATIENTS), patient).pipe(
      tap((patient) => console.log('NEW PATIENT' + patient.firstName))
    );
  }
}
