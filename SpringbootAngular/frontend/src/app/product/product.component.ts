import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../api.service';
import {Product} from '../../model/Product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private productService: ApiService) { }
  products: Product[];

  getProduct(): void {
      this.productService.getProduct().subscribe(
        (updateProducts) => {
          this.products = updateProducts;
          console.log(`this.products = ${JSON.stringify(this.products)}`);
        }
      );
  }

  ngOnInit() {
    this.getProduct();
  }

}
