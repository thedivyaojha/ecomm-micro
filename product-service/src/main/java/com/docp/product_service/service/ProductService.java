package com.docp.product_service.service;

import com.docp.product_service.dto.ProductRequest;
import com.docp.product_service.dto.ProductResponse;
import com.docp.product_service.model.Product;
import com.docp.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product newProduct = new Product();
        newProduct.setProductName(productRequest.name());
        newProduct.setProductDescription(productRequest.description());
        newProduct.setProductPrice(productRequest.price());
        newProduct.setStock(productRequest.stock());
        newProduct.setProductImageUrl(productRequest.imageUrl());
        newProduct.setProductCategory(productRequest.category());
        Product savedProduct = productRepository.save(newProduct);

        return mapToProductResponse(savedProduct);
    }


    public ProductResponse getProductById(Long id) {
        log.info("product validation request reveived for {} from cart service" , id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream().map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existingProduct.setProductName(productRequest.name());
        existingProduct.setProductDescription(productRequest.description());
        existingProduct.setProductPrice(productRequest.price());
        existingProduct.setStock(productRequest.stock());
        existingProduct.setProductImageUrl(productRequest.imageUrl());
        existingProduct.setProductCategory(productRequest.category());
        Product updatedProduct = productRepository.save(existingProduct);
        return mapToProductResponse(updatedProduct);
    }

    private ProductResponse mapToProductResponse(Product newProduct) {
        return new ProductResponse(
                newProduct.getProductName(),
                newProduct.getProductDescription(),
                newProduct.getProductPrice(),
                newProduct.getProductImageUrl(),
                newProduct.getProductCategory()
        );
    }


    public void deleteProduct(Long id) {
        Product productTobeDeleted = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(productTobeDeleted);

    }

    public ProductResponse getProductByName(String name) {
        Product product = productRepository.findByProductName(name)
                .orElseThrow(() -> new RuntimeException("Product not found with name: " + name));
        return mapToProductResponse(product);
    }
}
