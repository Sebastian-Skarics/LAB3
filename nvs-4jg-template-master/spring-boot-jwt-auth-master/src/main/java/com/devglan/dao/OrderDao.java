package com.devglan.dao;

import com.devglan.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {

    public List<Order> findAll();
}
