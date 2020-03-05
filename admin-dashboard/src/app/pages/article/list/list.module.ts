import { NgModule, } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListComponent } from "./list.component";
import { SharedModule } from "../../../shared/shared.module";
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { FormsModule} from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';
import { MarkdownModule } from 'ngx-markdown';


@NgModule({
  declarations: [ListComponent],
  imports: [
    CommonModule,SharedModule,NzDrawerModule,FormsModule,NzFormModule,MarkdownModule
  ],
  exports:[]
})
export class ListModule { }
