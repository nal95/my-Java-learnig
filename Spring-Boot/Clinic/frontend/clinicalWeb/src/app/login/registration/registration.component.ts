import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Users} from "../../models/users";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['../login.component.css']
})
export class RegistrationComponent implements OnInit {

  constructor(private readonly authService: AuthService) {
    this.signupForm = new FormGroup<any>({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      userName: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      title: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    })
  }

  user!: Users;
  signupForm!: FormGroup;

  ngOnInit() {
    this.user = {firstName: '', lastName: '', username: '', email: '', password: '', title: ''};
  }

  public titles = [
    {value: 'Dr', viewValue: 'Dr'},
    {value: 'Med', viewValue: 'Med'},
    {value: 'Passive', viewValue: 'Passive'}
  ];

  public signup() {
    this.user.firstName = this.signupForm.get('firstName')?.value;
    this.user.lastName = this.signupForm.get('lastName')?.value;
    this.user.username = this.signupForm.get('userName')?.value;
    this.user.email = this.signupForm.get('email')?.value;
    this.user.password = this.signupForm.get('password')?.value;
    this.user.title = this.signupForm.get('title')?.value;
    this.authService.signup(this.user).subscribe((res) => console.log("Resp: ", res))
  }
}
