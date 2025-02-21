package com.example.product_services.controllers;


import com.example.product_services.entity.Product;
import com.example.product_services.entity.ProductDTO;
import com.example.product_services.services.ProductServices;
import com.example.product_services.services.ProductServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductControllers {

    @Autowired
    private ProductServices productServices;



    @GetMapping()
    public ResponseEntity<List<Product>> getProducts(){
            return productServices.getAllProducts();
    }

    @GetMapping("hasproduct/{id}")
    public ResponseEntity<Boolean> hasProduct(@PathVariable int id){
        return productServices.hasProduct(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        return productServices.getProductById(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addProducts(@RequestBody ProductDTO productDTO){
        return productServices.addProducts(productDTO);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        return productServices.deleteProducts(id);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id,@RequestBody ProductDTO productDTO){
        return productServices.updateProduct(id,productDTO);
    }

}
