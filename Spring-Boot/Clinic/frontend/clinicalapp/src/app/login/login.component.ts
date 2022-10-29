import { Component } from '@angular/core';
import {ClincalHttpService} from "../services/clincal-http.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor( private readonly clincalHttpService:ClincalHttpService) {
  }

  login(email: string, pwd: string) {
    this.clincalHttpService.login(email,pwd).subscribe(
      (res)=>console.log(res)
    )
  }
}
