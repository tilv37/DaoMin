import { Component, OnInit } from '@angular/core';
import { CategoryModel } from "../../model/catagoryModel";
import { ResponseDTOModel } from "../../model/reponseDTOModel";
import { CategoryAdminServiceService } from "../../service/category-admin-service.service";

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

  constructor(private categoryService:CategoryAdminServiceService) { }

  ngOnInit() {
    this.getAllCategory();
  }

  addNewCategory():void{
    this.categoryService.addNewCatgory(this.inputValue).subscribe(x=>{
      console.log(x);
      this.getAllCategory();
    })
  }

  getAllCategory():void{
    this.categoryService.getAllCategory(this.pageNo-1,this.pageSize).subscribe(x=>{
      this.loading=false;
      this.total=x.data["totalElements"];
      this.categoryInView=x.data["content"];
    })
  }

}
