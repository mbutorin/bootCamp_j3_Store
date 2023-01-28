package com.colvir.j3.store.service;

import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.dto.ProductReviewDto;
import com.colvir.j3.store.dto.UserDto;

import java.util.List;

public interface ProductReviewService {
    ProductReviewDto save(ProductReviewDto productReviewDto);
    ProductReviewDto update(ProductReviewDto productReviewDto);
    void deleteById(Long id);
    List<ProductReviewDto> findByProductId(Long productId);

    List<ProductReviewDto> findAll();
}
