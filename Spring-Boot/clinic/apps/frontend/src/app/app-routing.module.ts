import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DataTableComponent} from "./clinic/data-table/data-table.component";
import {PatientRegistrationComponent} from "./clinic/patient-registration/patient-registration.component";

const routes: Routes = [
  {
    path: 'registration',
    component: PatientRegistrationComponent,
  },
  {
    path: 'data-table',
    component: DataTableComponent,
  },
  {path: '**', redirectTo: 'registration'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
