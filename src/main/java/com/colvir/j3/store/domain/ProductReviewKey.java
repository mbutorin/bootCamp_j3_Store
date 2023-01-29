package com.colvir.j3.store.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
//@AllArgsConstructor
public class ProductReviewKey implements Serializable {
//    private static final long serialVersionUID = 3743426181899305753L;

    @Column(name = "product_id")
    private Long productId;


    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductReviewKey that = (ProductReviewKey) o;
        return Objects.equals(productId, that.productId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, userId);
    }

}
