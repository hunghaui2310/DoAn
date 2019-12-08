package com.spring.angular.service;

import com.spring.angular.dto.ProductDTO;
import com.spring.angular.helper.SearchRequest;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProduct();

    List<ProductDTO> searchProductByName(SearchRequest searchRequest);
}
