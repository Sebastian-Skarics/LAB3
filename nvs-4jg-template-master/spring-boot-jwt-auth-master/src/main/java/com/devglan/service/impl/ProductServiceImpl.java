package com.devglan.service.impl;

import com.devglan.dao.ProductDao;
import com.devglan.model.Product;
import com.devglan.model.ProductDto;
import com.devglan.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public List<Product> findActiveProducts() {
        Date now = new Date();
        return productDao.findAllByValidFromLessThanEqualAndValidToGreaterThanEqual(now,now);
    }

    @Override
    public Product findById(Long id) {
        Assert.notNull(id, "productId must not be null");
        Optional<Product> product = productDao.findById(id);
        Assert.isTrue(product.isPresent(),"product with id "+id+" does not exist");
        return product.get();
    }

    @Override
    public Product updateProduct(ProductDto productDto) {
        Assert.notNull(productDto, "productDto must not be null");
        Assert.notNull(productDto.getId(), "productDto.id must not be null");
        Product product = findById(productDto.getId());
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    @Override
    public Product saveProduct(ProductDto product) {
        Product newProduct = new Product();
        BeanUtils.copyProperties(product, newProduct);
        return productDao.save(newProduct);
    }

    @Override
    public void deactivate(Long id) {
        Product toUpdate = findById(id);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        toUpdate.setValidTo(cal.getTime());
    }

    @Override
    public List<Product> findActiveProductsByNameLike(String name) {
        List<Product> products = findActiveProducts();
        return products.stream().filter(p -> p.getName().toUpperCase().contains(name.toUpperCase())).collect(Collectors.toList());
    }

}
