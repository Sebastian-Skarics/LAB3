package com.devglan.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ProductOrderKey implements Serializable {

    @Column(name = "product_id")
    Long productId;

    @Column(name = "order_id")
    Long orderId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}

