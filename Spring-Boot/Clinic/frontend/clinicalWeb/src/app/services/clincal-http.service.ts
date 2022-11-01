import {Injectable} from '@angular/core';
import {URL_LOGIN, URL_USER} from "../util/api-routes";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserResponse, Users} from "../models/users";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClincalHttpService {

  constructor( private readonly httpClient:HttpClient) {}

/*  public createUser (user:Users): Observable<UserResponse>{
    const headers = { 'content-type': 'application/json' };
    const body = JSON.stringify(user)
    return this.httpClient.post<UserResponse>(URL_USER, body, {headers:headers})
  }

  public getUser (uuId: string): Observable<UserResponse>{
    return this.httpClient.get<UserResponse>(URL_USER + `${uuId}`);
  }*/

  public login (email: string, password: string): Observable<any>{
    const httpHeaders = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Content-Type', 'application/x-www-form-urlencoded');

    const body = {
      username: email,
      password: password
    }
    return this.httpClient.post<any>(URL_LOGIN, {email,password},{
      withCredentials:true,
      headers: httpHeaders
    });
  }
}
