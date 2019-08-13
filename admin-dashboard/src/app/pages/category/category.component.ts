import { Component, OnInit } from '@angular/core';
import { CategoryModel } from "../../model/catagoryModel";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

  categoryList=[
    {
      id:1,
      catagoryName:'cate1',
      postNum:123
    },
    {
      id:2,
      catagoryName:'cate2',
      postNum:123
    },
    {
      id:31,
      catagoryName:'ca33te1',
      postNum:123
    },
    {
      id:14,
      catagoryName:'cate1',
      postNum:123
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}
