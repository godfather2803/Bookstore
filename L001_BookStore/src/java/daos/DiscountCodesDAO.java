/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.DiscountCodesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class DiscountCodesDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public DiscountCodesDTO getAvailableDiscountCode(String userID, String discountID, Date currentDate) throws Exception{
        DiscountCodesDTO discountCode = new DiscountCodesDTO();
        String sql = "SELECT discountID, [percent] FROM tblDiscount"
                + " WHERE discountID = ?"
                + " AND isUsed = ?"
                + " AND userID = ?"
                + " AND date >= ?";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountID);
            preStm.setBoolean(2, false);
            preStm.setString(3, userID);
            preStm.setDate(4, currentDate);
            rs = preStm.executeQuery();
            if(rs.next()){
                discountCode.setDiscountID(rs.getString("discountID"));
                discountCode.setPercent(rs.getInt("percent"));
            }
        }catch(SQLException | NamingException e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return discountCode;
    }
    
    public Boolean useDiscountCode(String discountID)throws Exception{
        Boolean isSuccess = false;
        String sql = "UPDATE tblDiscount SET"
                + " isUsed = ?"
                + " WHERE discountID = ?";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setString(2, discountID);
            preStm.executeUpdate();
            isSuccess = true;
        }catch(SQLException | NamingException e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return isSuccess;
    }
    
    public Boolean insert(DiscountCodesDTO discountCode) throws Exception{
        Boolean isSuccess = false;
        String sql = "INSERT INTO tblDiscount (discountID, percent, date, userID, isUsed)"
                + " VALUES (?,?,?,?,?)";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountCode.getDiscountID());
            preStm.setInt(2, discountCode.getPercent());
            preStm.setDate(3, discountCode.getDate());
            preStm.setString(4, discountCode.getUserID());
            preStm.setBoolean(5, false);
            preStm.executeUpdate();
            isSuccess = true;
        }catch(SQLException | NamingException e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return isSuccess;
    }
}
