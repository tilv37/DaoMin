import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ResponseDTOModel } from "../model/reponseDTOModel";
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class TagAdminServiceService {

  constructor(private http: HttpClient) { }

  addNewTag(tagName: string): Observable<ResponseDTOModel> {
    return this.http.post<ResponseDTOModel>('http://127.0.0.1:8080/api/admin/v1/tags/' + tagName, null);
  }

  getAllTage(pageNo: number = 0, pageSize: number = 10): Observable<ResponseDTOModel> {
    return this.http.get<ResponseDTOModel>('http://127.0.0.1:8080/api/admin/v1/tags',
      {
        params: new HttpParams().append("pageNo", `${pageNo}`).append("pageSize", `${pageSize}`)
      });
  }
}
