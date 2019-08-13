import { NgModule } from '@angular/core';

import { WelcomeRoutingModule } from './welcome-routing.module';

import { WelcomeComponent } from './welcome.component';

import { SharedModule } from "../../shared";

import { NgxEchartsModule } from "ngx-echarts"


@NgModule({
  imports: [WelcomeRoutingModule, SharedModule,NgxEchartsModule],
  declarations: [WelcomeComponent],
  exports: [WelcomeComponent]
})
export class WelcomeModule { }
