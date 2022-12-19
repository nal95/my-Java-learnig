import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DataTableComponent} from "./clinic/data-table/data-table.component";
import {RegistrationComponent} from "./patient/registration/registration.component";
import {DetailsComponent} from "./patient/details/details.component";
import {AnalyzeComponent} from "./patient/analyze/analyze.component";

const routes: Routes = [
  {
    path: 'registration',
    component: RegistrationComponent,
  },
  {
    path: 'patient-details/:id',
    component: DetailsComponent,
  },
  {
    path: 'patient-analyze/:id',
    component: AnalyzeComponent,
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
