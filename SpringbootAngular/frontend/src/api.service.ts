import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {Product} from './model/Product';
import {catchError, tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private homeURL = 'http://localhost:8080/product/fakeData';
  constructor(
    private http: HttpClient
  ) { }

  getProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(this.homeURL).pipe(
      tap(receiveProduct => console.log(`receiveProduct = ${JSON.stringify('receiveProduct')}`)),
      catchError(error => of([]))
    );
  }
}
