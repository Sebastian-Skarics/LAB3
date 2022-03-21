package com.devglan.service.impl;

import com.devglan.model.Product;
import com.devglan.model.ProductDto;
import com.devglan.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testSaveProductAndFindActiveProducts() {
        String productName = "my test product"+new Random().nextLong();

        ProductDto productDto = new ProductDto();
        productDto.setName(productName);
        productDto.setDescription("description");
        productDto.setImageName("image.png");
        productDto.setPrice(10.4);

        Calendar from = Calendar.getInstance();
        from.add(Calendar.YEAR,-1);
        productDto.setValidFrom(from.getTime());

        Calendar to = Calendar.getInstance();
        to.add(Calendar.YEAR,1);
        productDto.setValidTo(to.getTime());

        Product product = productService.saveProduct(productDto);
        Assert.assertNotNull(product);
        Assert.assertNotNull(product.getId());

        List<Product> products = productService.findActiveProducts();
        products = products.stream().filter(p -> p.getName().equals(productName)).collect(Collectors.toList());

        Assert.assertEquals(products.size(), 1);
        Product foundProdcut = products.get(0);

        Assert.assertEquals(foundProdcut.getDescription(),"description");
        // TODO weitere Assertions

    }
}
