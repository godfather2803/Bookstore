/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.OrdersDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class OrdersDAO {
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
    
    public ArrayList<OrdersDTO> getOrdersByUserID(String userID)throws Exception{
        ArrayList<OrdersDTO> orderList = new ArrayList<>();
        String sql = "SELECT orderID, userID, totalPrice, date, discountID"
                + " FROM tblOrders"
                + " WHERE userID = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            while(rs.next()){
            int orderID = rs.getInt("orderID");
            String rUserID = rs.getString("userID");
            Long totalPrice = rs.getLong("totalPrice");
            Date date = rs.getDate("date");
            String discountID = rs.getString("discountID");
            OrdersDTO order = new OrdersDTO(orderID, userID, totalPrice, date, discountID);
            orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return orderList;
    }
    
    public boolean insert(OrdersDTO order) throws Exception{
        boolean isSuccess = false;
        String sql = "INSERT INTO tblOrders (userID, totalPrice, date, discountID)"
                + " VALUES (?,?,?,?)";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, order.getUserID());
            preStm.setLong(2, order.getTotalPrice());
            preStm.setDate(3, order.getDate());
            preStm.setString(4, order.getDiscountID());
            preStm.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return isSuccess;
    }
}
