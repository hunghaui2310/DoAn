export class SearchRequest {
  productName: string;
  categoryId: number;
  price: number;

  constructor(productName?: string, categoryId?: number, price?: number) {
    this.productName = productName;
    this.categoryId = categoryId;
    this.price = price;
  }
}
