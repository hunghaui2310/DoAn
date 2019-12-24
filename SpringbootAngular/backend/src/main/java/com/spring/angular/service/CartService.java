package com.spring.angular.service;

import com.spring.angular.dto.CartDTO;

import java.math.BigInteger;

public interface CartService {

    public BigInteger getNumCart(Long userId) throws Exception;

    public String updateNumCart(Long userId) throws Exception;

    public CartDTO getCartByUser(Long userId) throws Exception;

    String removeProFromCart(Long userId) throws Exception;
}
