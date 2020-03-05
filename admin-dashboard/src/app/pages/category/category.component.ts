import { Component, OnInit } from '@angular/core';
import { CategoryModel } from "../../model/catagoryModel";
import { ResponseDTOModel } from "../../model/reponseDTOModel";
import { CategoryAdminServiceService } from "../../service/category-admin-service.service";
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  inputValue:string;
  categoryInView:CategoryModel[]=[];
  pageNo:number = 1;
  pageSize:number =10;
  total:number=1;
  loading = false;

  constructor(private categoryService:CategoryAdminServiceService,
    private message: NzMessageService) { }

  ngOnInit() {
    this.getAllCategory();
  }

  addNewCategory():void{
    if(this.inputValue){
      this.categoryService.addNewCatgory(this.inputValue).subscribe(x=>{
        if(x.status==true){
          this.getAllCategory();
          this.inputValue='';
        }
      })
    }else{
      this.message.info("请先输入内容");
    }
  }

  getAllCategory():void{
    this.categoryService.getAllCategory(this.pageNo-1,this.pageSize).subscribe(x=>{
      this.loading=false;
      this.total=x.data["totalElements"];
      this.categoryInView=x.data["content"];
    })
  }

}
