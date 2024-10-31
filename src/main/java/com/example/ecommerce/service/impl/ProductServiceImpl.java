package com.example.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product productAux = productRepository.findById(id).get();
        productAux.setName(product.getName());
        productAux.setPrice(product.getPrice());   
        productAux.setName(product.getName());   
        productAux.setCategory(product.getCategory());   
        productAux.setUrlImg(product.getUrlImg());
        return productRepository.save(productAux);   
    }

    @Override
    public Optional<Product> deleteProduct(Long id) {
        Optional<Product> productAux = getProductById(id);
        productRepository.deleteById(id);
        return productAux;
    }
    
}
