import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {ApiResponse} from '../model/api.response';

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) {
  }

  private baseUrl: string = 'http://localhost:8081/product';

  getProducts(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(this.baseUrl);
  }
}
