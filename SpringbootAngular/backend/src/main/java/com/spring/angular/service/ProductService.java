package com.spring.angular.service;

import com.spring.angular.dto.ProductDTO;
import com.spring.angular.helper.SearchRequest;
import com.spring.angular.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProduct();

    List<ProductDTO> searchProductByName(SearchRequest searchRequest);

    ProductDTO getProductById(Long id);

    List<String> getImageByProId(Long id);

    List<ProductDTO> getProByNumLike();
}
