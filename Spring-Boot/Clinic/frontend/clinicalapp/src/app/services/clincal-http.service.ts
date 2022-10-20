import {Injectable} from '@angular/core';
import {URL_USER} from "../util/api-routes";
import {HttpClient} from "@angular/common/http";
import {UserResponse, Users} from "../models/users";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClincalHttpService {

  constructor( private readonly httpClient:HttpClient) {}

  public createUser (user:Users): Observable<UserResponse>{
    const headers = { 'content-type': 'application/json' };
    const body = JSON.stringify(user)
    return this.httpClient.post<UserResponse>(URL_USER, body, {headers:headers})
  }

  public getUser (uuId: string): Observable<UserResponse>{
    return this.httpClient.get<UserResponse>(URL_USER + `${uuId}`);
  }
}
