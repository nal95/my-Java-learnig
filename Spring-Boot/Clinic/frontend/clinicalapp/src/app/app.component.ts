import { Component } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Clinical Service';
  public isAuthenticated = false;

  constructor(private  route:Router) {
  }

  public logout(): void {
    this.route.navigateByUrl('');
  }
}
