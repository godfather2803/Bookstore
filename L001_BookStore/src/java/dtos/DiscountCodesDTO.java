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
public class DiscountCodesDTO {
    private String discountID;
    private String userID;
    private int percent;
    private Date date;
    private Boolean isUsed;

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public DiscountCodesDTO() {
    }

    public DiscountCodesDTO(String discountID, String userID, int percent, Date date, Boolean isUsed) {
        this.discountID = discountID;
        this.userID = userID;
        this.percent = percent;
        this.date = date;
        this.isUsed = isUsed;
    }
    
    @Override
    public String toString(){
        return "{discountID: }" + discountID + ", userID: " + userID + ", percent: " + percent + ", isUsed: " + isUsed + "}";
    }
}
