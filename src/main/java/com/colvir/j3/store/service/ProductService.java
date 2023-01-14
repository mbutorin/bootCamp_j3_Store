package com.colvir.j3.store.service;

import com.colvir.j3.store.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto update(ProductDto productDto);
//    void deleteByLogin(String login);
//    UserDto findByLogin(String login);
}
