import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ResponseDTOModel } from "../model/reponseDTOModel";
import { NewPostModel } from "../model/newPostModel";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostAdminService {

  constructor(private http: HttpClient) { }

  getAllPostSummary(pageNo:number,pageSize:number){
    return this.http.get<ResponseDTOModel>('http://127.0.0.1:8080/api/admin/v1/post',
      {
        params: new HttpParams().append("pageNo", `${pageNo}`).append("pageSize", `${pageSize}`)
      });
  }

  deletePostById(postId:number){
    return this.http.delete('http://127.0.0.1:8080/api/admin/v1/post/'+postId);
  }

  translate(content:string){
    return this.http.get('http://127.0.0.1:8080/api/admin/v1/translate',
    {
      params:new HttpParams().append("q",`${content}`)
    });
  }

  addOne(post:NewPostModel){
    return this.http.post('http://127.0.0.1:8080/api/admin/v1/post',post);
  }

  getOne(postId:string){
    return this.http.get('http://127.0.0.1:8080/api/admin/v1/post/'+postId);
  }

  modifyOne(postId:string,post:NewPostModel){
    return this.http.put('http://127.0.0.1:8080/api/admin/v1/post/'+postId,post);
  }
}
