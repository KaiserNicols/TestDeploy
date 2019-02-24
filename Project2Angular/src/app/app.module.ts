import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
//import { PastRecsComponent } from './past-recs/past-recs.component';
import { NewRecComponent } from './new-rec/new-rec.component';
import { ProfileComponent } from './profile/profile.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ChartsComponent } from './charts/charts.component';
import { HttpClientModule } from '@angular/common/http';
import { UserService, User } from './user.service';
import { RecommendationService } from './recommendation.service';
import { Recommendation } from './recommendation';

import { GoogleChartsModule } from 'angular-google-charts';
import { RecsService } from './recs.service';
import { NavbarService } from './navbar.service';

@NgModule({
  declarations: [
    AppComponent,
  //  PastRecsComponent,
    NewRecComponent,
    ProfileComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    ChartsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    GoogleChartsModule
    ],
  providers: [ UserService, User, RecsService, NavbarService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
