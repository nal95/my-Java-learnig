import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule } from '@angular/forms';
import {HomeModule} from "./home/home.module";
import {LoginModule} from "./login/login.module";
import { MaterialModule } from './material.module';
import {HttpClientModule} from "@angular/common/http";
import {httpInterceptorProviders} from "./helpers/auth.interceptor";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HomeModule,
    LoginModule,
    BrowserAnimationsModule,
    FormsModule,
    MaterialModule,
    HttpClientModule,
  ],

  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
