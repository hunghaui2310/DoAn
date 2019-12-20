package com.spring.angular.service.impl;

import com.spring.angular.dto.ProductDTO;
import com.spring.angular.helper.DataUtil;
import com.spring.angular.helper.SearchRequest;
import com.spring.angular.model.Product;
import com.spring.angular.repository.ProductRepo;
import com.spring.angular.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Object[]> lstObject = productRepo.getProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();

        String proName = ""; int price =0;
        Long numLike; String discount = "";
        String img; Long lngId;
        for(Object[] objects : lstObject){
            ProductDTO productDTO = new ProductDTO();
            lngId = DataUtil.safeToLong(objects[0]);
            proName = String.valueOf(objects[1]);
            price = (int) objects[2];
            numLike = DataUtil.safeToLong(objects[3]);
            discount = String.valueOf(objects[4]);
            img = String.valueOf(objects[5]);

            productDTO.setId(lngId);
            productDTO.setProductName(proName);
            productDTO.setPrice(price);
            productDTO.setNumLike(numLike);
            productDTO.setDiscount(discount);
            productDTO.setUrlImage(img);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> searchProductByName(SearchRequest searchRequest) {
        List<Object[]> list = productRepo.searchProduct(searchRequest);
        List<ProductDTO> productDTOList = new ArrayList<>();
        String proName = ""; int price =0;
        Long numLike; String discount = "";
        String img; Long lngId;
        for(Object[] objects : list){
            ProductDTO productDTO = new ProductDTO();
            lngId = DataUtil.safeToLong(objects[0]);
            proName = String.valueOf(objects[1]);
            price = (int) objects[2];
            numLike = DataUtil.safeToLong(objects[3]);
            discount = String.valueOf(objects[4]);
            img = String.valueOf(objects[5]);

            productDTO.setId(lngId);
            productDTO.setProductName(proName);
            productDTO.setPrice(price);
            productDTO.setNumLike(numLike);
            productDTO.setDiscount(discount);
            productDTO.setUrlImage(img);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }
}
