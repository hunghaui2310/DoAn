import { Component, OnInit } from '@angular/core';
import {Title} from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private titleHome: Title
  ) {this.titleHome.setTitle('Đồ gỗ Huy Hùng'); }

  ngOnInit() {
  }

}
