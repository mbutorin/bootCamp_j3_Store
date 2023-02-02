package com.colvir.j3.store.repository;

import com.colvir.j3.store.domain.ProductEntity;
import com.colvir.j3.store.domain.ProductReviewEntity;
import com.colvir.j3.store.dto.ProductReviewDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductReviewRepository extends CrudRepository<ProductReviewEntity, Long> {
    Set<ProductReviewEntity> findByProduct(ProductEntity product);

//    @Query("select p from ProductReviewEntity p where p.productId = ?1")
    List<ProductReviewDto> findByProductId(Long productId);

//    @Query("select p from ProductReviewEntity p, UserEntity u where p.userId = u.id and u.login = ?1")
//    List<ProductReviewDto> findByUserLogin(String userLogin);

}
