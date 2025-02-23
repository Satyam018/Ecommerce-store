package com.example.product_services.services;


import com.example.product_services.Dao.ProductRepository;
import com.example.product_services.entity.Product;
import com.example.product_services.entity.ProductDTO;
import com.example.product_services.entity.ReduceItemOrderedDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServicesImpl implements ProductServices {


    @Autowired
    private ProductRepository productRepository;


    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()){
            throw new IllegalArgumentException("No Product Found");
        }
        return new ResponseEntity<>(product.get(),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> hasProduct(int id) {
        boolean hasProduct= productRepository.getProductById(id);
        return new ResponseEntity<>(hasProduct,HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Boolean> reduceItemOrder(List<ReduceItemOrderedDTO> reduceItemOrderedDTOList) {


        for (ReduceItemOrderedDTO reduceItemOrderedDTO:reduceItemOrderedDTOList){
            if(checkQuanity(reduceItemOrderedDTO))return new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
        }

        for (ReduceItemOrderedDTO reduceItemOrderedDTO:reduceItemOrderedDTOList){
            reduceQuanity(reduceItemOrderedDTO);
        }

        return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);

    }

    @Override
    public ResponseEntity<String> restoreStock(List<ReduceItemOrderedDTO> reduceItemOrderedDTO) {
        for (ReduceItemOrderedDTO reduceItemOrderedDTO1:reduceItemOrderedDTO){
            Product product=productRepository.findById(reduceItemOrderedDTO1.getProductId())
                    .get();
            product.setQuantity(product.getQuantity()+reduceItemOrderedDTO1.getQuantity());

            productRepository.save(product);
        }

        return new ResponseEntity<>("Success",HttpStatus.OK);
    }

    private void reduceQuanity(ReduceItemOrderedDTO reduceItemOrderedDTO){
        Product product=productRepository.findById(reduceItemOrderedDTO.getProductId())
                .get();

        product.setQuantity(product.getQuantity()-reduceItemOrderedDTO.getQuantity());

        productRepository.save(product);


    }

    private boolean checkQuanity(ReduceItemOrderedDTO reduceItemOrderedDTO){
        Optional<Product> productOptional=productRepository.findById(reduceItemOrderedDTO.getProductId());
        if(productOptional.isEmpty())return false;
        if(productOptional.get().getQuantity()<reduceItemOrderedDTO.getQuantity())return false;
        return true;
    }


    @Override
    public ResponseEntity<String> addProducts(ProductDTO productDTO) {

        if (productDTO.getQuantity() < 0||productDTO.getPrice()<=0) throw new IllegalArgumentException("Quantity cannot be negative");
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setCategory(productDTO.getCategory());
        product.setAvailablitiyStatus(true);

        productRepository.save(product);
        return new ResponseEntity<>("Product added succesfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteProducts(Integer id) {
        Optional<Product> product =productRepository.findById(id);
        if(product.isEmpty())throw new IllegalArgumentException("Product cannot be deleted!");
        productRepository.delete(product.get());
        return new ResponseEntity<>("Product Deleted Succesfully!",HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Product> updateProduct(Integer id, ProductDTO productDTO) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()) throw  new IllegalArgumentException("Product cannot be updated");
        Product updatedProduct=product.get();
        if(productDTO.getName()!=null && !productDTO.getName().isEmpty())updatedProduct.setName(productDTO.getName());
        if(productDTO.getDescription()!=null && !productDTO.getDescription().isEmpty())updatedProduct.setDescription(productDTO.getDescription());
        if(productDTO.getPrice()>0)updatedProduct.setPrice(productDTO.getPrice());
        if(productDTO.getCategory()!=null && !productDTO.getCategory().isEmpty())updatedProduct.setCategory(productDTO.getCategory());
        if(productDTO.getQuantity()>=0)updatedProduct.setQuantity(productDTO.getQuantity());

        productRepository.save(updatedProduct);

        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }


}
