package com.example.ecommerce.entity;

import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ArrayList<Long> productsId;
    private Long userId;
    private double totalPrice;


    public Cart() {}


    public Cart(ArrayList<Long> productsId, Long userId, double totalPrice) {
        this.productsId = productsId;
        this.userId = userId;
        this.totalPrice = totalPrice;
    }


    public Long getId() {
        return this.id;
    }

    public ArrayList<Long> getProductsId() {
        return this.productsId;
    }

    public void setProductsId(ArrayList<Long> productsId) {
        this.productsId = productsId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
