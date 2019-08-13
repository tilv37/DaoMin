import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { 
  NzGridModule,
  NzCardModule,
  NzTableModule,
  NzDividerModule,
  NzIconModule,
  NzButtonModule,
  NzInputModule,
  NzModalModule,
  NzMessageModule,
  NzDatePickerModule,
  NzSelectModule  
} from "ng-zorro-antd";

const nzModule=[
  NzGridModule,
  NzCardModule,
  NzTableModule,
  NzDividerModule,
  NzIconModule,
  NzButtonModule,
  NzInputModule,
  NzModalModule,
  NzMessageModule,
  NzDatePickerModule,
  NzSelectModule  
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ...nzModule
  ],
  exports:[
    CommonModule,
    ...nzModule]
})
export class SharedModule { }
