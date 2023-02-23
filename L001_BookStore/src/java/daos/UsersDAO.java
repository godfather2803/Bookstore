/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UsersDTO;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class UsersDAO {
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
    
    public String checkLogin(UsersDTO user) throws Exception{
        String role = null;
        String sql = "SELECT tblRoles.roleName"
                + " FROM tblUsers INNER JOIN tblRoles"
                + " ON tblUsers.roleID = tblRoles.roleID"
                + " WHERE tblUsers.userID = ?"
                + " AND tblUsers.password = ?"
                + " AND tblUsers.status = ?";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, user.getUserID());
            preStm.setString(2, user.getPassword());
            preStm.setBoolean(3, true);
            rs = preStm.executeQuery();
            if(rs.next()){
                role = rs.getString("roleName");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return role;
    }
    
    public ArrayList<UsersDTO> getAll() throws Exception{
        String sql = "SELECT userID, name, address, phone FROM tblUsers";
        ArrayList<UsersDTO> listUser = new ArrayList<>();
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()){
                UsersDTO user = new UsersDTO();
                user.setUserID(rs.getString("userID"));
                user.setName(rs.getString("name"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                listUser.add(user);
            }
        } catch (Exception e) {
            
        } finally{
            closeConnection();
        }
        return listUser;
    }
}
