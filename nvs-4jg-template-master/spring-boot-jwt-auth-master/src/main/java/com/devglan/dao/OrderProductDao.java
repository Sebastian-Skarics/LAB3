package com.devglan.dao;

import com.devglan.model.Order;
import com.devglan.model.OrderProduct;
import com.devglan.model.ProductOrderKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDao extends CrudRepository<OrderProduct, ProductOrderKey> {

    List<OrderProduct> findAll();

    List<OrderProduct> findByOrder(Order order);
}
