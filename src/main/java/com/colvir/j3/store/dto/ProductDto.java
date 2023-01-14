package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Integer group_id;
    private String producer;
    private String type;
    private Float price;
    private Integer qty;

    public ProductDto(final ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.group_id = entity.getGroup_id();
        this.producer = entity.getProducer();
        this.type = entity.getType();
        this.price = entity.getPrice();
        this.qty = entity.getQty();
    }
}

