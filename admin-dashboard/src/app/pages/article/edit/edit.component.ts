import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd';
import { ActivatedRoute } from '@angular/router';
import { NewPostModel } from "../../../model/newPostModel";
import { ResponseDTOModel } from "../../../model/reponseDTOModel";
import { TagAdminServiceService } from "../../../service/tag-admin-service.service";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {

  markdown: string;
  isVisible: boolean = false;
  selectedValue:string;
  newPost:NewPostModel=new NewPostModel();
  tags:string[]=[];


  constructor(private modalService: NzModalService,
    private route: ActivatedRoute,
    private tagService:TagAdminServiceService ) { }

  ngOnInit() {
    this.newPost.content = "dasdas";
    let id=this.route.snapshot.paramMap.get('id');
    if(id){
      this.newPost.content=id;
    }
    this.initTagAndCate();
    this.newPost.createTM=new Date();
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

  savePost():void{
    console.log(this.newPost);
  }

  initTagAndCate(){
    this.tagService.getAllTage(0,10).subscribe((x:ResponseDTOModel)=>{
      console.log(x);
      let tagData=x.data.content;
      tagData.forEach(element => {
        this.tags.push(element.tagName);
      });
    })
  }

}
