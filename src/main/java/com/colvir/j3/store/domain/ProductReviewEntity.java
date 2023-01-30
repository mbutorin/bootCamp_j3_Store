package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.dto.ProductReviewDto;
import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.repository.ProductRepository;
import com.colvir.j3.store.repository.UserRepository;
import com.colvir.j3.store.service.ProductService;
import com.colvir.j3.store.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "product_review", schema = "public")
@NoArgsConstructor
public class ProductReviewEntity {

    @EmbeddedId
    private ProductReviewKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "create_dt")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Timestamp createDateTime;
    @Column(name = "correct_dt")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Timestamp correctDateTime;
    @Column
    private String review;

    public ProductReviewEntity(final ProductReviewDto productReviewDto) {

//        this.product = productReviewDto.getProduct_id();
//        this.user = UserRepository.  productReviewDto.getUser_id();
        // как найти по ИД UserEntity / ProductEntity ????????????????????????????????????????????
//        this.user = UserDto(userService.findById(productReviewDto.getUser_id()));

        this.createDateTime = productReviewDto.getCreateDateTime();
        this.correctDateTime = productReviewDto.getCorrectDateTime();
        this.review = productReviewDto.getReview();
    }
}

