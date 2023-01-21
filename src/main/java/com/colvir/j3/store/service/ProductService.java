package com.colvir.j3.store.service;

import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.dto.UserDto;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    ProductDto update(ProductDto productDto);
    void deleteByName(String name);
    ProductDto findByName(String name);

    List<ProductDto> findAll();
}
