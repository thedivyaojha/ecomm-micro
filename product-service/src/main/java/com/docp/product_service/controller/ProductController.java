package com.docp.product_service.controller;


import com.docp.product_service.dto.ProductRequest;
import com.docp.product_service.dto.ProductResponse;
import com.docp.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById( @PathVariable Long id ){
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        List<ProductResponse> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        ProductResponse savedProduct = productService.createProduct(productRequest);
        log.info("Product created : {} ", savedProduct);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse>updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest){
        ProductResponse productResponse= productService.updateProduct(id,productRequest);
        return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");

    }
     @GetMapping("/name/{name}")
       public ResponseEntity<ProductResponse> getProductByName(@PathVariable("name") String productName) {
            ProductResponse response = productService.getProductByName(productName);
            return ResponseEntity.ok(response);

    }

}


//http://localhost:8080/api/v1/product
