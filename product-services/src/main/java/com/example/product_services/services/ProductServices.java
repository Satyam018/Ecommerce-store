package com.example.product_services.services;

import com.example.product_services.entity.Product;
import com.example.product_services.entity.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServices {

     ResponseEntity<List<Product>> getAllProducts() ;


     ResponseEntity<String> addProducts(ProductDTO productDTO) ;


    ResponseEntity<Product> getProductById(Integer id);

    ResponseEntity<String> deleteProducts(Integer id);

    ResponseEntity<Product> updateProduct(Integer id, ProductDTO productDTO);
}
