import { Component, OnInit } from '@angular/core';
import { TagAdminServiceService } from "../../service/tag-admin-service.service";
import { NzMessageService } from 'ng-zorro-antd/message';
import { TagModel } from "../../model/tagModel";
import { ResponseDTOModel } from "../../model/reponseDTOModel";

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.scss']
})
export class TagComponent implements OnInit {

  inputValue: string='';
  tagsInView:TagModel[] =[];
  pageNo:number = 1;
  pageSize:number =10;
  total:number=1;
  loading = false;

  constructor(private tagService: TagAdminServiceService,
    private message: NzMessageService) { }

  ngOnInit() {
    // this.inputValue='dasdasd';
    this.getAllTags();
  }

  addNewTag(): void {
    if(this.inputValue){
      this.tagService.addNewTag(this.inputValue).subscribe(
        x => {
          localStorage.removeItem("allTags");
          this.getAllTags();
          this.inputValue='';
        }
      );
    }else{
      this.message.info("请先输入内容");
    }
  }

  getAllTags():void{
    this.loading=true;
    this.tagService.getAllTage(this.pageNo-1,this.pageSize).subscribe((x:ResponseDTOModel)=>{
      this.loading=false;
      console.log(x.msg);
      this.total=x.data["totalElements"];
      this.tagsInView=x.data["content"];
      localStorage.setItem("allTags",JSON.stringify(x));
      console.log(this.tagsInView)
    })
      
  }

}
