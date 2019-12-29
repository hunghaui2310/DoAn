const URL_BASE = 'http://localhost:8083';

export const config = {
  category_API: URL_BASE + '/category/getCategory',
  product_API: URL_BASE + '/product/getProduct',
  search_product: URL_BASE + '/product/search',
  img_product: URL_BASE + '/product/image',
  product_detail: URL_BASE + '/product/detail',
  show_order: URL_BASE + '/order/showAll'
};
