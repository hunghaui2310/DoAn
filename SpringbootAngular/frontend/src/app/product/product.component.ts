import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../api.service';
import {Product} from '../../model/Product';
import {Observable} from 'rxjs';
import {Category} from '../../model/Category';
import {HttpClient} from '@angular/common/http';
import {config} from '../../app-routing/application.config';
import {ProductService} from '../service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private productService: ProductService
  ) { }
  products: Product[];
  currentP = 1;
  pageSize: 12;
  mstrstatus = '';

  getProduct() {
      this.productService.productAPI().subscribe(
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

  productDetail(id: number) {
    this.productService.proDetailAPI(id);
    console.log('productId = ', id);
  }

  ngOnInit() {
    this.getProduct();
  }

}
