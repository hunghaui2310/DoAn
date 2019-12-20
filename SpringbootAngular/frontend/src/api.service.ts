import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Product} from './model/Product';
import {catchError, tap} from 'rxjs/operators';
import {Category} from './model/Category';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private homeURL = 'http://localhost:8083/product/fakeData';
  private categoryURL = 'http://localhost:8083/category/getCategory';
  constructor(
    private http: HttpClient
  ) { }

  getProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(this.homeURL).pipe(
      tap(receiveProduct => console.log(`receiveProduct = ${JSON.stringify('receiveProduct')}`)),
      catchError(error => of([]))
    );
  }
  getCategory(): Observable<Category[]> {
    // @ts-ignore
    return this.http.get<Category[]>(this.categoryURL).pipe()(
      tap(receiveCategory => console.log(`receiveCategory = ${JSON.stringify(receiveCategory)}`)),
      catchError(err => of([]))
    );
  }
}
