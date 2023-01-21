package com.colvir.j3.store.controller;

import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.dto.ProductReviewDto;
import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.service.ProductReviewService;
import com.colvir.j3.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product_review")
public class ProductReviewController {

    @Autowired
    public ProductReviewController(
            @Qualifier("productReviewServiceDb") final ProductReviewService productReviewService
    ) {
        this.productReviewService = productReviewService;
    }

    private final ProductReviewService productReviewService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public ProductReviewDto save(@Valid @RequestBody final ProductReviewDto productReviewDto) {
        return productReviewService.save(productReviewDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ProductReviewDto update(@Valid @RequestBody final ProductReviewDto productReviewDto) {
        return productReviewService.update(productReviewDto);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam(name = "id") final Long id) {
        productReviewService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductReviewDto> findByProductId(@RequestParam(name = "product_id") final Long product_id) {
        return productReviewService.findByProductId(product_id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProductReviewDto> findByAll() {
        return productReviewService.findAll();
    }

}
