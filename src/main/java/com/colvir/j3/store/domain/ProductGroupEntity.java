package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.ProductGroupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "product_group", schema = "public")
@NoArgsConstructor
public class ProductGroupEntity {

    @Id
    @GeneratedValue(generator = "product_grp_seq")
    private int id;

    @Column(name="parent_id")
    private int parentGroup;

    @Column
    private String name;

    @ManyToMany(mappedBy = "groups")
    Set<ProductEntity> products;

    public ProductGroupEntity(final ProductGroupDto productGroupDto) {
        this.id = productGroupDto.getId();
        this.parentGroup = productGroupDto.getParentGroup();
        this.name = productGroupDto.getName();
    }
}

