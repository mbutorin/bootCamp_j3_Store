package com.colvir.j3.store.dto;

import com.colvir.j3.store.domain.ProductGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGroupDto {
    private Integer id;
    private Integer parentGroup;
    private String name;

    public ProductGroupDto(final ProductGroupEntity entity) {
        this.id = entity.getId();
        this.parentGroup = entity.getParentGroup();
        this.name = entity.getName();
    }
}

