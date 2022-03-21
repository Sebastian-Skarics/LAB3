package com.devglan.dao;

import com.devglan.model.Order;
import com.devglan.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderProductDao orderProductDao;

    @Autowired
    private ProductDao productDao;

    private Order generateTestOrder(){
        Order order = new Order();
        order.setLastName("Name1");
        order.setTotalPrice(15.15);
        return order;
    }

    @Test
    public void saveOrder() {
        Order order = generateTestOrder();
        orderDao.save(order);
    }

    @Test
    public void findById() {
        Optional<Order> orderOpt = orderDao.findById(new Long(-1));
        Assert.assertTrue(orderOpt.isPresent());
        Order order = orderOpt.get();
        Assert.assertEquals("unfinished",order.getLastName());
    }

    @Test
    public void saveRelation(){
        Product product = new Product();
        product.setDescription("desc1");
        product.setPrice(15.00);
        product.setName("name1");
        product.setValidFrom(new Date());
        product.setValidTo(new Date());
        product=productDao.save(product);

        Order order = generateTestOrder();
        order=orderDao.save(order);

        //TODO: finish
    }
}
