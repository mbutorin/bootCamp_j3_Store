package com.colvir.j3.store.controller;

import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api( tags = "Products")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductController(
            @Qualifier("productServiceDb") final ProductService productService
    ) {
        this.productService = productService;
    }

    private final ProductService productService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "This method is used to create a new product")
    @GetMapping
    public ProductDto save(@Valid @RequestBody final ProductDto productDto) {
        return productService.save(productDto);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ProductDto update(@Valid @RequestBody final ProductDto productDto) {
        return productService.update(productDto);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ProductDto findByName(@RequestParam(name = "name") final String name) {
        return productService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, value = "item_by_id")
    public ProductDto findById(@RequestParam(name = "id") final Long id) {
        return productService.findById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteByName(@RequestParam(name = "name") final String name) {
        productService.deleteByName(name);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<ProductDto> findByAll() {
        return productService.findAll();
    }

}
