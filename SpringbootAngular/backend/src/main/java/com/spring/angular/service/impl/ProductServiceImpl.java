package com.spring.angular.service.impl;

import com.spring.angular.dto.ProductDTO;
import com.spring.angular.model.Product;
import com.spring.angular.repository.ProductRepo;
import com.spring.angular.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<ProductDTO> getAllProduct() {
        long lngId = 0;
        List<Object[]> lstObject = productRepo.getProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();

        String proName = ""; int price =0;
        int numLike = 0; String discount = "";
        String img;
        for(Object[] objects : lstObject){
            ProductDTO productDTO = new ProductDTO();
            proName = String.valueOf(objects[0]);
            price = (int) objects[1];
            numLike = (int) objects[2];
            discount = String.valueOf(objects[3]);
            img = String.valueOf(objects[4]);

            productDTO.setId(lngId++);
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
