package com.colvir.j3.store.service.impl;

import com.colvir.j3.store.domain.ProductEntity;
import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.exception.RecordBadData;
import com.colvir.j3.store.exception.RecordNotFoundException;
import com.colvir.j3.store.exception.UserNotFoundException;
import com.colvir.j3.store.repository.ProductRepository;
import com.colvir.j3.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceDb implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto save(final ProductDto productDto) {
        try {
            return new ProductDto(
                    productRepository.save(
                            new ProductEntity(productDto)
                    )
            );
        } catch (Exception e) {
            throw new RecordBadData("Can't save product: " + e.getMessage());
        }
    }

    @Override
    //@Transactional
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    public ProductDto update(final ProductDto productDto) {
        final ProductEntity current = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new UserNotFoundException("Can't find product by id " + productDto.getId()));
        current.setName(productDto.getName());
        current.setType(productDto.getType());
        current.setProducer(productDto.getProducer());
        current.setPrice(productDto.getPrice());
        current.setQty(productDto.getQty());
        try {
            return new ProductDto(productRepository.save(current));
        } catch (Exception e) {
            throw new RecordBadData("Can't save product: " + e.getMessage());
        }
    }

    @Override
    public void deleteByName(final String name) {
        productRepository.deleteById(findByName(name).getId());
    }

    @Override
    public ProductDto findById(Long id) {
        return productRepository.findById(id)
                .map(ProductDto::new)
                .orElseThrow(() -> new RecordNotFoundException("Can't find product by id: " + id));
    }

    @Override
    public ProductDto findByName(final String name) {
        return productRepository.findByName(name)
                .map(ProductDto::new)
                .orElseThrow(() -> new RecordNotFoundException("Can't find product by name: " + name));
    }

    @Override
    public List<ProductDto> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

}
