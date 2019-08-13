import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ArticleComponent } from "./article.component";
import { ArticleRoutingModule } from "./article-routing.module";
import { ListModule } from "./list/list.module";
import { EditModule } from "./edit/edit.module";



@NgModule({
  declarations: [ArticleComponent],
  imports: [
    CommonModule,ArticleRoutingModule,ListModule,EditModule
  ]
})
export class ArticleModule { }
