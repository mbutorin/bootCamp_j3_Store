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
    private Long product_id;
    private Long user_id;
    private Timestamp createDateTime;
    private Timestamp correctDateTime;
    private String review;

    public ProductReviewDto(final ProductReviewEntity entity) {
        this.product_id = entity.getProduct().getId();
        this.user_id = entity.getUser().getId();
        this.createDateTime = entity.getCreateDateTime();
        this.correctDateTime = entity.getCorrectDateTime();
        this.review = entity.getReview();
    }
}

