package com.colvir.j3.store.service.impl;

import com.colvir.j3.store.domain.ProductEntity;
import com.colvir.j3.store.domain.ProductReviewEntity;
import com.colvir.j3.store.dto.ProductReviewDto;
import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.exception.RecordNotFoundException;
import com.colvir.j3.store.exception.RecordBadData;
import com.colvir.j3.store.repository.ProductRepository;
import com.colvir.j3.store.repository.ProductReviewRepository;
import com.colvir.j3.store.service.ProductReviewService;
import com.colvir.j3.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductReviewServiceDb implements ProductReviewService {

    private final ProductReviewRepository productReviewRepository;

    @Override
    public ProductReviewDto save(ProductReviewDto productReviewDto) {
        productReviewDto.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
        try {
            return new ProductReviewDto(
                    productReviewRepository.save(
                            new ProductReviewEntity(productReviewDto)
                    )
            );
        } catch (Exception e) {
            throw new RecordNotFoundException("Can't save product review: " + e.getMessage());
        }

    }

    @Override
    public ProductReviewDto update(ProductReviewDto productReviewDto) {
        final ProductReviewEntity current = productReviewRepository.findById(productReviewDto.getId())
                .orElseThrow(() -> new RecordNotFoundException("Can't find product review by id " + productReviewDto.getId()));

        current.setCorrectDateTime(new Timestamp(System.currentTimeMillis()));
        current.setReview(productReviewDto.getReview());
        // never update fields user_id, product_id
        // update only current timestamp, and review text

        try {
            return new ProductReviewDto(productReviewRepository.save(current));
        } catch (Exception e) {
            throw new RecordBadData("Can't save product review: " + e.getMessage());
        }

    }

    @Override
    public void deleteById(Long id) {
        productReviewRepository.deleteById(id);
    }

    @Override
    public List<ProductReviewDto> findByProductId(Long productId) {
        return productReviewRepository.findByProductId(productId);
    }

    @Override
    public List<ProductReviewDto> findByUserLogin(String userLogin) {
        return productReviewRepository.findByUserLogin(userLogin);
    }

    @Override
    public List<ProductReviewDto> findAll() {
        return StreamSupport.stream(productReviewRepository.findAll().spliterator(), false)
                .map(ProductReviewDto::new)
                .collect(Collectors.toList());

    }
}
