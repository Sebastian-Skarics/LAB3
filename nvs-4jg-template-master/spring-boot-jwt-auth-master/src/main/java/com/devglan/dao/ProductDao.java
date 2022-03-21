package com.devglan.dao;

import com.devglan.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, Long> {

    List<Product> findAllByValidFromLessThanEqualAndValidToGreaterThanEqual(Date validFrom, Date validUntil);

}
