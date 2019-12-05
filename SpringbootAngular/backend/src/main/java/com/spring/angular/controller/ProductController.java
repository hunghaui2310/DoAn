package com.spring.angular.controller;

import com.spring.angular.dto.ProductDTO;
import com.spring.angular.helper.ApiResponse;
import com.spring.angular.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProduct")
    public ApiResponse getAllProduct(){
        try {
            return ApiResponse.build(HttpServletResponse.SC_OK, true, "", productService.getAllProduct());
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponse.build(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, false, "ERROR!", null);
        }
    }

    @GetMapping("/fakeData")
    public List<ProductDTO> getFakeData(){
        List<ProductDTO> list = productService.getAllProduct();
        try{
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
