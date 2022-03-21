package com.devglan.service.impl;

import com.devglan.dao.OrderDao;
import com.devglan.dao.OrderProductDao;
import com.devglan.dao.ProductDao;
import com.devglan.model.*;
import com.devglan.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderProductDao orderProductDao;


    @Override
    public List<OrderDto> getOrders(boolean canceled, boolean finished) {

        List<Order> orders = orderDao.findAll();

        List<Order> filteredOrders = new ArrayList<>();

        //TODO improve performance by selecting with dao
        for (Order o : orders) {
            boolean canceledOrder = o.getCanceled() != null;
            boolean finishedOrder = o.getFinished() != null;
            if (canceled == canceledOrder && finished == finishedOrder) {
                filteredOrders.add(o);
            }
        }

        List<OrderDto> orderDtos=mapToDto(filteredOrders);
        return orderDtos;
    }

    private List<OrderDto> mapToDto(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for(Order order:orders){
            List<OrderProduct> orderProducts = orderProductDao.findByOrder(order);
            OrderDto orderDto=new OrderDto();
            BeanUtils.copyProperties(order, orderDto, "productList");
            List<OrderProductDto> orderProductDtos = new ArrayList<>();
            for(OrderProduct orderProduct:orderProducts) {
                OrderProductDto orderProductDto = new OrderProductDto();
                orderProductDto.setQuantity(orderProduct.getQuantity());
                BeanUtils.copyProperties(orderProduct.getProduct(), orderProductDto);
                orderProductDtos.add(orderProductDto);
            }
            orderDto.setProductList(orderProductDtos);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    @Override
    public Order save(OrderDto orderDto) {
        Order order = new Order();
        if (orderDto != null) {
            BeanUtils.copyProperties(orderDto, order, "productList");
            order = orderDao.save(order);
            copyProductList(orderDto,order);
            return order;
        }
        return null;
    }

    private void copyProductList(OrderDto orderDto, Order order){
        //TODO: update products already in the list
        if (orderDto.getProductList() != null) {
            for (OrderProductDto productDto : orderDto.getProductList()) {
                Assert.notNull(productDto, "productDto must not be null");
                Assert.notNull(productDto.getId(),"productDto.Id must not be null");
                Optional<Product> product = productDao.findById(productDto.getId());
                Assert.isTrue(product.isPresent(), "Product with Id "+productDto.getId()+" not found");

                //TODO check if product is currently valid
                OrderProduct orderProduct=new OrderProduct();
                ProductOrderKey key = new ProductOrderKey();
                key.setProductId(product.get().getId());
                key.setOrderId(order.getId());
                orderProduct.setId(key);
                orderProduct.setOrder(order);
                orderProduct.setProduct(product.get());
                orderProduct.setQuantity(productDto.getQuantity());
                orderProductDao.save(orderProduct);
            }
        }
    }

    @Override
    public Order update(OrderDto orderDto) {
        if (orderDto != null) {
            Optional<Order> orderOpt = orderDao.findById(orderDto.getId());
            if (orderOpt.isPresent()) {
                Order order = orderOpt.get();
                BeanUtils.copyProperties(orderDto, order, "productList");
                copyProductList(orderDto,order);
            }
        }
        return null;
    }

    @Override
    public Order findById(Long id) {
        return orderDao.findById(id).orElse(null);
    }

    @Override
    public void setFinished(Long id) {
        Order order = findById(id);
        Assert.notNull(order.getFinished(), "order is alredy finished");
        Assert.notNull(order.getCanceled(), "order is already canceled");
        order.setFinished(new Date());
    }

    @Override
    public void setCanceled(Long id) {
        Order order = findById(id);
        Assert.notNull(order.getFinished(), "order is alredy finished");
        Assert.notNull(order.getCanceled(), "order is already canceled");
        order.setCanceled(new Date());
    }
}
