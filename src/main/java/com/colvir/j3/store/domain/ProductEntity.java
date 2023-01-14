package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "product", schema = "public")
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "product_seq")
    private Long id;

    @Column
    private String name;
    @Column
    private Integer group_id;
    @Column
    private String producer;
    @Column
    private String type;
    @Column
    private Float price;
    @Column
    private Integer qty;

    public ProductEntity(final ProductDto productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.group_id = productDto.getGroup_id();
        this.producer = productDto.getProducer();
        this.type = productDto.getType();
        this.price = productDto.getPrice();
        this.qty = productDto.getQty();
    }
}

