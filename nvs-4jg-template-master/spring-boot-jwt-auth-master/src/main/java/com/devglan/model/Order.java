package com.devglan.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order2")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String salutation;
    @Column
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String zipCode;
    @Column
    private Date canceled;
    @Column
    private Date finished;
    @Column(nullable = false)
    private Double totalPrice;

    // TODO Funktioniert nicht, Liste immer leer
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, orphanRemoval = true)
    //@JoinColumn(name = "product_id")
    //List<OrderProduct> productList;

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
}
