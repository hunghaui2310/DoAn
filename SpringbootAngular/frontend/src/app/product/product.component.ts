import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../api.service';
import {Product} from '../../model/Product';
import {Observable} from 'rxjs';
import {Category} from '../../model/Category';
import {HttpClient} from '@angular/common/http';
import {config} from '../../app-routing/application.config';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private productService: ApiService
  ) { }
  products: Product[];
  currentP = 1;
  pageSize: 12;
  mstrstatus = '';

  // @ts-ignore
  productAPI(): Observable<Category[]> {
    return this.http.get<Category[]>(config.product_API);
  }

  getProduct() {
      this.productAPI().subscribe(
        (dataProducts) => {
          this.products = dataProducts['data'];
          },
        error => (console.error('Không có dữ liệu'))
      );
  }

  pageChange(page: number) {
    let total = this.currentP * 12;
    if (this.currentP * 12 > this.products.length) {
      total = this.products.length;
    }
    // for (let i = (this.currentP - 1) * 10; i < total; i++) {
    //   this.products[i].check = false;
    // }
    this.mstrstatus = '';
    this.currentP = page;
    console.log('fffff', this.currentP);
  }

  ngOnInit() {
    this.getProduct();
  }

}
