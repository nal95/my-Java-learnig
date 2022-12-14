import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {Patient} from "../model/data.model";
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
}
