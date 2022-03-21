package com.devglan.model;

import java.util.Date;
import java.util.List;

public class OrderDto {
    private Long id;

    private String salutation;

    private String firstName;

    private String lastName;

    private String street;

    private String city;

    private String zipCode;

    private Date canceled;

    private Date finished;

    private Double totalPrice;

    private List<OrderProductDto> productList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getCanceled() {
        return canceled;
    }

    public void setCanceled(Date canceled) {
        this.canceled = canceled;
    }

    public Date getFinished() {
        return finished;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderProductDto> getProductList() {
        return productList;
    }

    public void setProductList(List<OrderProductDto> productList) {
        this.productList = productList;
    }
}
