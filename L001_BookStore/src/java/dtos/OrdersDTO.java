/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class OrdersDTO {
    private int orderID;
    private String userID;
    private long totalPrice;
    private Date date;
    private String discountID;

    public OrdersDTO() {
    }

    public OrdersDTO(String userID, long totalPrice, Date date, String discountID) {
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.date = date;
        this.discountID = discountID;
    }

    public OrdersDTO(int orderID, String userID, long totalPrice, Date date, String discountID) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.date = date;
        this.discountID = discountID;
    }

    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }
    
    
}
