import {NgModule} from '@angular/core';
import {DataTableComponent} from './data-table/data-table.component';
import {MaterialModule} from "../material.module";
import {CommonModule} from "@angular/common";


@NgModule({
  declarations: [
    DataTableComponent,
  ],
  exports: [],
  imports: [
    CommonModule,
    MaterialModule,
  ]
})
export class ClinicModule {
}
