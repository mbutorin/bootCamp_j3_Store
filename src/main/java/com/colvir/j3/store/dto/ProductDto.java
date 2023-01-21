package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.ProductEntity;
import com.colvir.j3.store.domain.ProductGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private ProductGroupEntity groupId;
    private String producer;
    private String type;
    private BigDecimal price;
    private Integer qty;

    public ProductDto(final ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.groupId = entity.getGroupId();
        this.producer = entity.getProducer();
        this.type = entity.getType();
        this.price = entity.getPrice();
        this.qty = entity.getQty();
    }
}

