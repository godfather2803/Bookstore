/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author DELL
 */
public class BooksDTO implements Serializable{
 
    private String bookID;
    private String bookTitle;
    private long price;
    private int quantity;
    private Boolean status;
    private Date importDate;
    private String image;
    private String description;
    private String author;
    private String categoryName;
    private Integer categoryID;

    public BooksDTO() {
    }

    public BooksDTO(String bookID, String bookTitle, long price, int quantity, Boolean status, Date importDate, String image, String description, String author, Integer categoryID) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.importDate = importDate;
        this.image = image;
        this.description = description;
        this.author = author;
        this.categoryID = categoryID;
    }


    public BooksDTO(String bookID, String bookTitle, long price, int quantity, Boolean status, Date importDate, String image, String description, String author, String categoryName, Integer categoryID) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.importDate = importDate;
        this.image = image;
        this.description = description;
        this.author = author;
        this.categoryName = categoryName;
        this.categoryID = categoryID;
    }

    public BooksDTO(String bookID, String bookTitle, long price, int quantity, Boolean status, Date importDate, String image, String description, String author, String categoryName) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.importDate = importDate;
        this.image = image;
        this.description = description;
        this.author = author;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{ID: " + this.bookID + ", Title: " + this.bookTitle + ", Quantity: " + quantity + ", Price: " + price + ", Status: " + status + ", CategoryID: "+categoryID + ", Description: " + description + ", Author: " + author + "}";
    }
    
    
    
}
