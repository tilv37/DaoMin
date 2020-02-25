import { Component, OnInit } from '@angular/core';
import { NzModalService } from 'ng-zorro-antd';
import { ActivatedRoute } from '@angular/router';
import { NewPostModel } from "../../../model/newPostModel";
import { TagModel } from "../../../model/tagModel";
import { ResponseDTOModel } from "../../../model/reponseDTOModel";
import { TagAdminServiceService } from "../../../service/tag-admin-service.service";
import { PostAdminService } from "src/app/service/post-admin.service";
import { CategoryAdminServiceService } from "src/app/service/category-admin-service.service";
import { CategoryModel } from 'src/app/model/catagoryModel';
@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.scss']
})
export class EditComponent implements OnInit {

  timeFormat='yyyy-MM-dd HH:mm:ss'
  markdown: string;
  isVisible: boolean = false;
  selectedValue:string;
  newPost:NewPostModel=new NewPostModel();
  tags:TagModel[]=[];
  cates:CategoryModel[]=[];


  constructor(private modalService: NzModalService,
    private route: ActivatedRoute,
    private tagService:TagAdminServiceService,
    private postAdminService:PostAdminService,
    private cateAdminService:CategoryAdminServiceService) { }

  ngOnInit() {
     this.initTagAndCate();
    this.newPost.content = "dasdas";
    let id=this.route.snapshot.paramMap.get('id');
    if(id){
      //获取该ID的文章数据
      this.postAdminService.getOne(id).subscribe((x:ResponseDTOModel)=>{
        this.newPost=x.data;
      })
      this.newPost.content=id;
    }

    this.newPost.createTime=new Date();
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
    console.log("nw---->",this.newPost);
    let id=this.route.snapshot.paramMap.get('id');
    if(id){
      this.postAdminService.modifyOne(id,this.newPost).subscribe();
    }else{
      this.postAdminService.addOne(this.newPost).subscribe();
    }
  }

  initTagAndCate(){
    this.tagService.getAllTage(0,100).subscribe((x:ResponseDTOModel)=>{
      console.log(x);
      let tagData=x.data.content;
      tagData.forEach((element:TagModel) => {
        this.tags.push(element);
      });
    });

    this.cateAdminService.getAllCategory(0,100).subscribe((cate:ResponseDTOModel)=>{
      console.log(cate);
      let cateData=cate.data.content;
      cateData.forEach((element:CategoryModel) => {
        this.cates.push(element);
      });
    })

  }

  getTranslte(){
    if(this.newPost.title){
      //提交翻译接口
      this.postAdminService.translate(this.newPost.title).subscribe((x:ResponseDTOModel)=>{
        this.newPost.titleEn = x.data;
      })
    }
  }

}
