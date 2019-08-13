import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router"
import { ArticleSummary } from "../../../model/articleSummaryModel";
import { HelpUtils } from "../../../util/helpUtils";
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  helperUtils:HelpUtils=new HelpUtils();
  isVisible:boolean=false;
  isOkLoading:boolean = false;
  isAllChecked: boolean = false;
  itemsCheckedStatus: { [key: string]: boolean } = {};
  nowDisplayData: ArticleSummary[] = [];
  selectedNumber:number=0;

  articleInfoList = [
    {
      id: '1',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '2',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '3',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '4',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '5',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '6',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '7',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '8',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '9',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '10',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '11',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '12',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    },
    {
      id: '13',
      title: '第一篇文章',
      time: '2019-01-01',
      category: '默认分类',
      tag: '默认标签'
    }
  ]

  constructor(private message: NzMessageService,private router: Router) {
   }

  ngOnInit() {
  }

  showDeleteModal(){
    if(this.selectedNumber==0){
      this.message.info('还未选择任何一篇文章');
    }else{
      this.isVisible=true;
    }
  }

  deleteCancel(){
    this.isVisible=false;
  }

  deleteConfirm(){
    this.isOkLoading = true;
    setTimeout(() => {
      this.isVisible = false;
      this.isOkLoading = false;
    }, 3000);
  }

  refreshStatus():void{
    //遍历所有正在展示的数据，然后从itemsCheckedStatus中查询，是否都选上了，如果都选上了，就设置isAllChecked为true
    console.log(this.itemsCheckedStatus);
    this.isAllChecked=this.nowDisplayData.every(element => {
        return this.itemsCheckedStatus[element.id];
    });
    this.selectedNumber = this.nowDisplayData.filter(item => this.itemsCheckedStatus[item.id]).length;
  }

  currentPageDataChange($event: ArticleSummary[]): void {
    this.nowDisplayData = $event;
  }

  checkedChangeAll(value: boolean) {
    //select all checkbox
    this.nowDisplayData.forEach(item => (this.itemsCheckedStatus[item.id] = value));
    this.refreshStatus();
  }

  gotoUrl():void{
    this.router.navigate(['/article/edit']);
  }
}
