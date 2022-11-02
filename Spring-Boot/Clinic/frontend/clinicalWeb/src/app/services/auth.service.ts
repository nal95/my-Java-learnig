import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable, tap} from 'rxjs';
import {URL_LOGIN, URL_SIGNUP} from "../util/api-routes";
import {UserResponse, Users} from "../models/users";



@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    let body = `username=${username}&password=${password}`;
    return this.http.post<any>(
      URL_LOGIN,
      body,
    {
      headers:{
        'Content-Type': 'application/x-www-form-urlencoded',
      }
    }
    ).pipe(
      tap((res)=>console.log("RES: ", res))
    );
  }
  signup(user: Users): Observable<any> {
    return this.http.post<any>(
      URL_SIGNUP,
      user,
    );
  }
}
