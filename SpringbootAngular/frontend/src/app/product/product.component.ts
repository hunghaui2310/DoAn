import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import { ApiService } from '../../api.service';
import {Product} from '../../model/Product';
import {Observable} from 'rxjs';
import {Category} from '../../model/Category';
import {HttpClient} from '@angular/common/http';
import {config} from '../../app-routing/application.config';
import {ProductService} from '../service/product.service';
import {SearchRequest} from '../../model/search.request';
import {CartService} from '../service/cart.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit, AfterViewInit {
  @Input() productList: Product[];

  constructor(
    private http: HttpClient,
    private productService: ProductService,
    private cartService: CartService
  ) { }
  currentP = 1;
  pageSize: 12;
  mstrstatus = '';

  ngOnInit() {
    console.log(this.productList);
    // this.getProduct();
  }

  pageChange(page: number) {
    let total = this.currentP * 15;
    if (this.currentP * 15 > this.productList.length) {
      total = this.productList.length;
    }
    this.mstrstatus = '';
    this.currentP = page;
    console.log('page', this.currentP);
  }

  productDetail(id: number) {
    this.productService.proDetailAPI(id);
    location.replace('/product');
    console.log('productId = ', id);
  }

  addItemCart(userId: number) {
    this.cartService.addToCartAPI(userId);
    console.log('userId', userId);
  }

  ngAfterViewInit(): void {
    if (!this.productList) {
    } else {
      this.productList = this.productList;
      console.log(this.productList);
    }
  }

}
