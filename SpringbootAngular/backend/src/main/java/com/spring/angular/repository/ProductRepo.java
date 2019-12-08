package com.spring.angular.repository;

import com.spring.angular.helper.SearchRequest;
import com.spring.angular.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepo{

    List<Object[]> getProduct();

    List<Object[]> searchProduct(SearchRequest searchRequest);
}
