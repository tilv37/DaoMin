import { Component, OnInit } from '@angular/core';
import { TagAdminServiceService } from "../../service/tag-admin-service.service";
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

  constructor(private tagService: TagAdminServiceService) { }

  ngOnInit() {
    // this.inputValue='dasdasd';
    this.getAllTags();
  }

  addNewTag(): void {
    this.tagService.addNewTag(this.inputValue).subscribe(
      x => {
        console.log(x);
        this.getAllTags();
      }
    );
  }

  getAllTags():void{
    this.loading=true;
    this.tagService.getAllTage(this.pageNo-1,this.pageSize).subscribe((x:ResponseDTOModel)=>{
      this.loading=false;
      console.log(x.msg);
      this.total=x.data["totalElements"];
      this.tagsInView=x.data["content"];
      console.log(this.tagsInView)
    })
      
  }

}
