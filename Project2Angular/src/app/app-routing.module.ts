import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewRecComponent } from './new-rec/new-rec.component';
import { PastRecsComponent } from './past-recs/past-recs.component';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ChartsComponent } from './charts/charts.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full'},
  { path: 'new-rec', component: NewRecComponent },
  { path: 'past-recs', component: PastRecsComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'charts', component: ChartsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
