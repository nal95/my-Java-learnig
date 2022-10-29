import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private readonly httpService:AuthService) {
  }

  login(email: string, pwd: string) {
    this.httpService.login(email,pwd).subscribe({
      error:()=>alert("error by login"),
      next: (res)=>console.log(res)
      }
    )
  }
}
