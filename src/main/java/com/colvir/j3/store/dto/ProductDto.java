package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.ProductEntity;
import com.colvir.j3.store.domain.ProductGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String producer;
    private String type;
    private BigDecimal price;
    private Integer qty;

    private Set<ProductReviewDto> reviews = new HashSet<>();

    public ProductDto(final ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.producer = entity.getProducer();
        this.type = entity.getType();
        this.price = entity.getPrice();
        this.qty = entity.getQty();
    }
}

