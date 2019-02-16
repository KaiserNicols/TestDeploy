import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NewRecComponent } from './new-rec/new-rec.component';
import { PastRecsComponent } from './past-recs/past-recs.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: '', redirectTo: '/past-recs', pathMatch: 'full'},
  { path: 'new-rec', component: NewRecComponent },
  { path: 'past-recs', component: PastRecsComponent },
  { path: 'profile', component: ProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
