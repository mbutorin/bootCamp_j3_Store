package com.colvir.j3.store.repository;

import com.colvir.j3.store.domain.ProductReviewEntity;
import com.colvir.j3.store.dto.ProductReviewDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends CrudRepository<ProductReviewEntity, Long> {

//    @Query("select p from ProductReviewEntity p where p.productId = ?1")
    List<ProductReviewDto> findByProductId(Long productId);

//    @Query("select p from ProductReviewEntity p, UserEntity u where p.userId = u.id and u.login = ?1")
//    List<ProductReviewDto> findByUserLogin(String userLogin);

}
