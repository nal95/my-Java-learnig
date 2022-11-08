import { Component } from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private readonly autService:AuthService,
              private readonly route:Router) {
  }

  login(email: string, pwd: string) {
    this.autService.login(email,pwd).subscribe({
      error:()=>alert("error by login"),
      next: (res)=> this.route.navigateByUrl('/')
      }
    )
  }
}
