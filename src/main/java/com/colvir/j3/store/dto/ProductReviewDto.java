package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.ProductEntity;
import com.colvir.j3.store.domain.ProductGroupEntity;
import com.colvir.j3.store.domain.ProductReviewEntity;
import com.colvir.j3.store.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDto {

    private Long id;
    private ProductEntity product;
    private UserEntity user;
    private Timestamp createDateTime;
    private Timestamp correctDateTime;
    private String review;

    public ProductReviewDto(final ProductReviewEntity entity) {
        this.id = entity.getId();
        this.product = entity.getProductId();
        this.user = entity.getUserId();
        this.createDateTime = entity.getCreateDateTime();
        this.correctDateTime = entity.getCorrectDateTime();
        this.review = entity.getReview();
    }
}

