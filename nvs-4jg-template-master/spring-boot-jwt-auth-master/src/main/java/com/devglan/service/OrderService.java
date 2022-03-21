package com.devglan.service;

import com.devglan.model.Order;
import com.devglan.model.OrderDto;

import java.util.List;

public interface OrderService {
    public List<OrderDto> getOrders(boolean canceled, boolean finished);

    public Order save(OrderDto orderDto);

    public Order update(OrderDto orderDto);

    public Order findById(Long id);

    void setFinished(Long id);

    void setCanceled(Long id);

}
