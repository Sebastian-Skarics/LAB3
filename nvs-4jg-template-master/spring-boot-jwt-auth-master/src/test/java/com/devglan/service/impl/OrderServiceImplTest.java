package com.devglan.service.impl;

import com.devglan.dao.OrderDao;
import com.devglan.dao.OrderProductDao;
import com.devglan.model.*;
import com.devglan.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductDao orderProductDao;

    @Test
    public void getOrders_findUnfinished(){
        List<OrderDto> orders = orderService.getOrders(false, false);
        Assert.assertNotNull(orders);
        orders = orders.stream().filter(o -> o.getId() <= -1 && o.getId() >=-3).collect(Collectors.toList());
        Assert.assertEquals(orders.size(),1);
        Assert.assertEquals(orders.get(0).getLastName(),"unfinished");
    }

    @Test
    public void getOrders_findCanceled(){
        List<OrderDto> orders = orderService.getOrders(true, false);
        Assert.assertNotNull(orders);
        orders = orders.stream().filter(o -> o.getId() <= -1 && o.getId() >=-3).collect(Collectors.toList());
        Assert.assertEquals(orders.size(),1);
        Assert.assertEquals(orders.get(0).getLastName(),"canceled");
    }

    @Test
    public void getOrders_findFinished(){
        List<OrderDto> orders = orderService.getOrders(false, true);
        Assert.assertNotNull(orders);
        orders = orders.stream().filter(o -> o.getId() <= -1 && o.getId() >=-3).collect(Collectors.toList());
        Assert.assertEquals(orders.size(),1);
        Assert.assertEquals(orders.get(0).getLastName(),"finished");
    }

    @Test
    public void getOrders_findFinishedAndCanceled(){
        List<OrderDto> orders = orderService.getOrders(true, true);
        Assert.assertNotNull(orders);
        Assert.assertEquals(orders.size(),0);
    }

    //TODO should be split into two tests (save and find)
    @Test
    public void saveOrderWithProductAndFind(){
        OrderDto orderDto = new OrderDto();
        orderDto.setLastName("Name1");
        orderDto.setTotalPrice(15.0);

        List<OrderProductDto> products = new ArrayList<>();
        OrderProductDto productDto = new OrderProductDto();
        productDto.setId(new Long(-1));
        productDto.setQuantity(2);
        products.add(productDto);

        OrderProductDto productDto2 = new OrderProductDto();
        productDto2.setId(new Long(-2));
        productDto2.setQuantity(3);
        products.add(productDto2);

        orderDto.setProductList(products);

        Order order = orderService.save(orderDto);
        Assert.assertNotNull(order);
        Assert.assertNotNull(order.getId());

        final Order orderFound = orderService.findById(order.getId());
        Assert.assertNotNull(order);
        Assert.assertNotNull(order.getId());

        List<OrderProduct> productOrder = orderProductDao.findByOrder(order);
        Assert.assertEquals(2, productOrder.size());

        List<OrderDto> orderDtos = orderService.getOrders(false, false);
        Assert.assertEquals(2,orderDtos.size());
        Optional<OrderDto> foundOrderDto=orderDtos.stream().filter(dto -> dto.getId().equals(orderFound.getId())).findFirst();
        Assert.assertEquals(true,foundOrderDto.isPresent());
        Assert.assertEquals(2,foundOrderDto.get().getProductList().size());

    }
}
