import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoryComponent } from "./category.component";
import { CategoryRoutingModule } from "./category-routing.module";
import { SharedModule } from "../../shared/shared.module";
import { FormsModule} from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';


@NgModule({
  declarations: [CategoryComponent],
  imports: [
    CommonModule,CategoryRoutingModule,SharedModule,FormsModule,NzFormModule
  ]
})
export class CategoryModule { }
