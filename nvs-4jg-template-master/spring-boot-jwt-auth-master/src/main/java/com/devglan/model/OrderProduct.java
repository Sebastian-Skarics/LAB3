package com.devglan.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class OrderProduct {

    @EmbeddedId
    ProductOrderKey id;

    @ManyToOne
    @MapsId("product_id")
    Product product;

    @ManyToOne
    @MapsId("order_id")
    Order order;

    Integer quantity;

    public ProductOrderKey getId() {
        return id;
    }

    public void setId(ProductOrderKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
