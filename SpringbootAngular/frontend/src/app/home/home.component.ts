import { Component, OnInit } from '@angular/core';
import {Title} from '@angular/platform-browser';
import {ApiService} from '../../api.service';
import {Category} from '../../model/Category';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {config} from '../../app-routing/application.config';
import {SearchRequest} from '../../model/search.request';
import {HomeService} from '../service/home.service';
import {ProductService} from '../service/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private titleHome: Title,
    private searchHome: HomeService,
    private categoryService: ApiService,
    private productService: ProductService
  ) {this.titleHome.setTitle('Đồ gỗ Huy Hùng'); }
  categories: Category[];
  mnbrCateId;
  price;
  productName;
  mblnChec = false;
  searchModel: SearchRequest;
  products;

  // @ts-ignore
  getCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(config.category_API);
  }

  getComboboxCate() {
    this.getCategory().subscribe(
      vobjNext => {
        this.categories = vobjNext['data'];
      },
      error => (console.error('Không có dữ liệu'))
    );
  }

  showCate() {
  this.getCategory();
  }

  search() {
    this.productName = null;
    // this.categoryId = null;
    // this.price = null;

    this.searchModel = new SearchRequest(null,  this.mnbrCateId, this.price);
    console.log('search', this.searchModel);
    this.searchHome.search(this.searchModel).subscribe(
      data => {
        console.log(data['data']);
        this.products = data['data'];
      },
      error => {
        console.error('loi search', error);
      },
      () => {
        console.log('ok');
      }
    );
  }

  ngOnInit() {
    this.mnbrCateId = null;
    this.getComboboxCate();
    this.getProduct();
  }

  getProduct() {
    this.productService.productAPI().subscribe(
      (dataProducts) => {
        this.products = dataProducts['data'];
      },
      error => (console.error('Không có dữ liệu'))
    );
  }
}
