import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EditComponent  } from "./edit.component";
import { SharedModule } from "../../../shared/shared.module";
import { MarkdownModule } from 'ngx-markdown';
import { FormsModule} from '@angular/forms';
import { NzFormModule } from 'ng-zorro-antd/form';




@NgModule({
  declarations: [EditComponent],
  imports: [
    CommonModule,SharedModule,MarkdownModule.forRoot(),FormsModule,NzFormModule 
  ]
})
export class EditModule { }
