import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ResponseDTOModel } from "../model/reponseDTOModel";
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class CategoryAdminServiceService {

  constructor(private http: HttpClient) { }

  addNewCatgory(categoryName:string){
    return this.http.post<ResponseDTOModel>('http://127.0.0.1:8080/api/admin/v1/category/' + categoryName, null);
  }

  getAllCategory(pageNo:number,pageSize:number){
    return this.http.get<ResponseDTOModel>('http://127.0.0.1:8080/api/admin/v1/category',
      {
        params: new HttpParams().append("pageNo", `${pageNo}`).append("pageSize", `${pageSize}`)
      });
  }
}
