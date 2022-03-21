package com.devglan.service;

import com.devglan.model.Product;
import com.devglan.model.ProductDto;

import java.util.List;

public interface ProductService {

    List<Product> findActiveProducts();

    Product findById(Long id);

    Product saveProduct(ProductDto productDto);

    Product updateProduct(ProductDto productDto);

    public void deactivate(Long id);

    List<Product> findActiveProductsByNameLike(String name);
}
