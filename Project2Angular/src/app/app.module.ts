import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { PastRecsComponent } from './past-recs/past-recs.component';
import { NewRecComponent } from './new-rec/new-rec.component';
import { ProfileComponent } from './profile/profile.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HttpClientModule } from '@angular/common/http';
import { UserService, User } from './user.service';
import { NavbarService } from './navbar.service';

@NgModule({
  declarations: [
    AppComponent,
    PastRecsComponent,
    NewRecComponent,
    ProfileComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
    ],
  providers: [ UserService, User, NavbarService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
