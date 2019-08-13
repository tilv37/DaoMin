import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd';

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {

  markdown: string;
  isVisible: boolean = false;

  constructor(private modalService: NzModalService) { }

  ngOnInit() {
    this.markdown = "dasdas";
  }

  onKey(value: string): void {
    this.markdown = value;
  }

  preView(): void {
    this.isVisible=true;
  }

  handleOk():void{

    this.isVisible=false;
  }

}
