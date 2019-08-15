import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TagComponent } from './tag.component';
import { TagRoutingModule } from "./tag-routing.module";
import { FormsModule} from '@angular/forms';
import { SharedModule } from "../../shared/shared.module";


@NgModule({
  declarations: [TagComponent],
  imports: [
    CommonModule,TagRoutingModule,SharedModule,FormsModule
  ]
})
export class TagModule { }
