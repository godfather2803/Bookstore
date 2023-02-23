/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class CartItemsDTO implements Serializable {
    private String bookID;
    private String bookTitle;
    private int quantity;
    private Long price;

    public CartItemsDTO() {
    }

    public CartItemsDTO(String bookID, String bookTitle, int quantity, Long price) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{ID: " + this.bookID + "; Title: " + this.bookTitle + "; Quantity: " + quantity + "}";
    }
    
}
