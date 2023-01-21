package com.colvir.j3.store.domain;

import com.colvir.j3.store.dto.ProductGroupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "product_group", schema = "public")
@NoArgsConstructor
public class ProductGroupEntity {

    @Id
    @GeneratedValue(generator = "product_grp_seq")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private ProductGroupEntity parentGroup;

    @Column
    private String name;


    public ProductGroupEntity(final ProductGroupDto productGroupDto) {
        this.id = productGroupDto.getId();
        this.parentGroup = productGroupDto.getParentGroup();
        this.name = productGroupDto.getName();
    }
}

