/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author DELL
 */
public class OrderDetailDTO {
    private int detailID;
    private int orderID;
    private String bookID;
    private Long price;
    private int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int detailID, int orderID, String bookID, Long price, int quantity) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.bookID = bookID;
        this.price = price;
        this.quantity = quantity;
    }

    
    
    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
