package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.dto.ProductReviewDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "product_review", schema = "public")
@NoArgsConstructor
public class ProductReviewEntity {

    @Id
    @GeneratedValue(generator = "product_review_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity productId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userId;

    @Column(name = "create_dt")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Timestamp createDateTime;
    @Column(name = "correct_dt")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Timestamp correctDateTime;
    @Column
    private String review;

    public ProductReviewEntity(final ProductReviewDto productReviewDto) {
        this.id = productReviewDto.getId();
        this.productId = productReviewDto.getProduct();
        this.userId = productReviewDto.getUser();
        this.createDateTime = productReviewDto.getCreateDateTime();
        this.correctDateTime = productReviewDto.getCorrectDateTime();
        this.review = productReviewDto.getReview();
    }
}

