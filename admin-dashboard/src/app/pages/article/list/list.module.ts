import { NgModule, } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListComponent } from "./list.component";
import { SharedModule } from "../../../shared/shared.module";



@NgModule({
  declarations: [ListComponent],
  imports: [
    CommonModule,SharedModule
  ],
  exports:[]
})
export class ListModule { }
