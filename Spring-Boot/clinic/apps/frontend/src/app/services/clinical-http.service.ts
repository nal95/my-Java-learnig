import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {ClinicalData, NewPatient, NewPatientData, NewPatientResponse, Patient} from "../model/data.model";
import {ANALYZE, PATIENT, PATIENTS, pDATA} from "../clinic-backend-routes";

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

  public getPatientData(id: number): Observable<Patient> {
    return this.httpClient.get<Patient>(PATIENT+id).pipe(
      tap(() => console.log('Http GET Patient data...'))
    );
  }

  public registerNewPatient(patient: NewPatient): Observable<NewPatientResponse> {
    return this.httpClient.post<NewPatientResponse>((PATIENTS), patient).pipe(
      tap((_patient) => console.log('NEW PATIENT' + _patient.firstName))
    );
  }

  public addPatientData(data: NewPatientData): Observable<ClinicalData> {
    return this.httpClient.post<ClinicalData>((pDATA), data).pipe(
      tap((_data) => console.log('NEW DATA ...' + JSON.stringify(_data)))
    );
  }

  public getPatientAnalyze(id: number): Observable<Patient> {
    return this.httpClient.get<Patient>(ANALYZE+id).pipe(
      tap(() => console.log('Http GET Patient analyze...'))
    );
  }
}
