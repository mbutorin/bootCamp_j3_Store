package com.colvir.j3.store.service.impl;

import com.colvir.j3.store.domain.ProductEntity;
import com.colvir.j3.store.domain.UserEntity;
import com.colvir.j3.store.dto.ProductDto;
import com.colvir.j3.store.dto.ProductGroupDto;
import com.colvir.j3.store.dto.UserDto;
import com.colvir.j3.store.exception.UserBadData;
import com.colvir.j3.store.exception.UserNotFoundException;
import com.colvir.j3.store.repository.ProductRepository;
import com.colvir.j3.store.repository.UserRepository;
import com.colvir.j3.store.service.ProductService;
import com.colvir.j3.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
            throw new UserBadData("Can't save product: " + e.getMessage());
        }
    }

    @Override
    //@Transactional
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    public ProductDto update(final ProductDto productDto) {
        final ProductEntity current = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new UserNotFoundException("Can't find product by id " + productDto.getId()));
        current.setName(productDto.getName());
        current.setGroupId(productDto.getGroupId());
        current.setType(productDto.getType());
        current.setProducer(productDto.getProducer());
        current.setPrice(productDto.getPrice());
        current.setQty(productDto.getQty());
        try {
            return new ProductDto(productRepository.save(current));
        } catch (Exception e) {
            throw new UserBadData("Can't save product: " + e.getMessage());
        }
    }

    @Override
    public void deleteByName(final String name) {
        productRepository.deleteById(findByName(name).getId());
    }

    @Override
    public ProductDto findByName(final String name) {
        return productRepository.findByName(name)
                .map(ProductDto::new)
                .orElseThrow(() -> new UserNotFoundException("Can't find user by login: " + name));
    }

    @Override
    public List<ProductDto> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

}
