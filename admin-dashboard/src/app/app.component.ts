import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isCollapsed = false;

  constructor(private message: NzMessageService) {}

  clearCache(){
    localStorage.clear();
    this.message.info('Cache has benn clear');
  }
}
