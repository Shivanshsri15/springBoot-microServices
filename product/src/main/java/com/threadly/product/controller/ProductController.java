package com.threadly.product.controller;

import com.threadly.product.dao.Response;
import com.threadly.product.model.Product;
import com.threadly.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Response> getProducts() {
        try {
            List<Product> products = productService.getProducts();
            return ResponseEntity.ok(new Response("Products Fetched Successfully", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error fetching products: " + e.getMessage(), null));
        }
    }

    @PostMapping
    public ResponseEntity<Response> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("Product Created Successfully", createdProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error creating product: " + e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getProductById(@PathVariable UUID id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("Product Not Found", null));
            }
            return ResponseEntity.ok(new Response("Product Fetched Successfully", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error fetching product: " + e.getMessage(), null));
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Response> getProductBySlug(@PathVariable String slug) {
        try {
            Product product = productService.getProductBySlug(slug);
            if (product == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response("Product Not Found", null));
            }
            return ResponseEntity.ok(new Response("Product Fetched Successfully", product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Error fetching product: " + e.getMessage(), null));
        }
    }
}