import { Component, OnInit } from '@angular/core';
import {Title} from '@angular/platform-browser';
import {ApiService} from '../../api.service';
import {Category} from '../../model/Category';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {config} from '../../app-routing/application.config';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private http: HttpClient,
    private titleHome: Title,
    private categoryService: ApiService
  ) {this.titleHome.setTitle('Đồ gỗ Huy Hùng'); }
  categories: Category[];
  mnbrCateId;
  mblnChec = false;

  // @ts-ignore
  getCategory(): Observable<Category[]> {
    return this.http.get<Category[]>(config.category_API);
  }

  getComboboxCate() {
    this.getCategory().subscribe(
      vobjNext => {
        this.categories = vobjNext;
      },
      error => (console.error('Không có dữ liệu'))
    );
  }

  showCate() {
  this.getCategory();
  }
  ngOnInit() {
    this.getComboboxCate();
  }

}
