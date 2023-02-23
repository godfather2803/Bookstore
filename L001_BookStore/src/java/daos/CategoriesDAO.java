/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CategoriesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class CategoriesDAO {
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
    
    
    
    public ArrayList<CategoriesDTO> getAll() throws Exception{
        ArrayList<CategoriesDTO> categoriesList = new ArrayList<>();
        String sql = "SELECT categoryID, categoryName FROM tblCategories";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while(rs.next()){
                int categoryID = rs.getInt("categoryID");
                String categoryName = rs.getString("categoryName");
                CategoriesDTO category = new CategoriesDTO(categoryID, categoryName);
                categoriesList.add(category);
            }
        }catch(Exception e){
            
        }finally{
            closeConnection();
        }
        return categoriesList;
    }
}
