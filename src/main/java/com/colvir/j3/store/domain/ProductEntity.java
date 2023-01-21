package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private ProductGroupEntity groupId;
    @Column
    private String producer;
    @Column
    private String type;
    @Column
    private BigDecimal price;
    @Column
    private Integer qty;

    public ProductEntity(final ProductDto productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.groupId = productDto.getGroupId();
        this.producer = productDto.getProducer();
        this.type = productDto.getType();
        this.price = productDto.getPrice();
        this.qty = productDto.getQty();
    }
}

