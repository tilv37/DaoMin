import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router"
import { HelpUtils } from "../../../util/helpUtils";
import { NzMessageService } from 'ng-zorro-antd';
import { postSummaryModel } from "../../../model/postSummaryModel";
import { PostAdminService } from "../../../service/post-admin.service";
import { NzModalService } from 'ng-zorro-antd/modal';
import { NewPostModel } from "../../../model/newPostModel";
import { TagAdminServiceService } from "../../../service/tag-admin-service.service";
import { CategoryAdminServiceService } from "src/app/service/category-admin-service.service";
import { ResponseDTOModel } from "../../../model/reponseDTOModel";


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
  nowDisplayData: postSummaryModel[] = [];
  selectedNumber:number=0;

  pageNo:number = 1;
  pageSize:number =10;
  total:number=1;
  loading = false;
  postInView:postSummaryModel[] =[]; 

  editVisible:boolean = false;
  previewVisible:boolean=false;
  newPost:NewPostModel=new NewPostModel();
  tagNames:string[]=[];
  cateNames:string[]=[];
  dateFormat='yyyy-MM-dd HH:mm:ss';

  constructor(private message: NzMessageService,
    private router: Router,
    private postService:PostAdminService,
    private modalService: NzModalService,
    private tagService:TagAdminServiceService,
    private cateService:CategoryAdminServiceService) {
   }

  ngOnInit() {
    this.getAllPostSummary();
    this.tagService.getAllTagNames().subscribe((x:ResponseDTOModel)=>{
      this.tagNames=x.data;
    });

    this.cateService.getAllCategoryNames().subscribe(
      (x:ResponseDTOModel)=>{
        this.cateNames=x.data;
      }
    )
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

  currentPageDataChange($event: postSummaryModel[]): void {
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

  getAllPostSummary():void{
    this.postService.getAllPostSummary(this.pageNo-1,this.pageSize).subscribe(x=>{
      this.loading=false;
      this.total=x.data["totalElements"];
      this.nowDisplayData=x.data["content"];
    })
  }

  goEditPost(postId:number):void{
    this.router.navigate(['/article/edit',postId]);
  }


  deletePostById(postId:number):void{
    this.modalService.confirm({
      nzTitle:'<i>确认删除吗?</i>',
      nzOnOk: () => {
        this.message.info(`the post ${postId} has been deleted.`)
        // this.postService.deletePostById(postId).subscribe(x=>{
        //   this.getAllPostSummary();
        // })
      }
    })
  }

  openEditDrawer(): void {
    this.editVisible = true;
  }

  closeEditDrawer(): void {
    this.editVisible = false;
  }

  openPreview():void{
    console.log(this.newPost);
    this.previewVisible=true;
  }

  closePreview():void{
    this.previewVisible=false;
  }

  saveOrupdatePost(){
    this.postService.addOne(this.newPost).subscribe(
      x=>{
        this.message.info("保存成功")
      }
    )
  }
}
