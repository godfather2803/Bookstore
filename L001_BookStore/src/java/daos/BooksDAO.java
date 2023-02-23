/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.BooksDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import utils.DBUtilities;

/**
 *
 * @author DELL
 */
public class BooksDAO {
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final String BASE_SELECT = 
                "SELECT tblBooks.bookID, tblBooks.bookTitle, tblBooks.price,"
                + " tblBooks.quantity, tblBooks.status, tblBooks.importDate,"
                + " tblBooks.image, tblBooks.description, tblBooks.author,"
                + " tblCategories.categoryName, tblBooks.categoryID"
                + " FROM tblBooks INNER JOIN tblCategories ON tblBooks.categoryID = tblCategories.categoryID";

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
    public boolean checkBookExist(String bookID) throws Exception{
        boolean isExist = false;
        String sql = "SELECT bookID FROM tblBooks WHERE bookID = ?";
        try{
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            rs = preStm.executeQuery();
            if(rs.next()){
                isExist = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isExist;
    }
    
    public boolean insert(BooksDTO book) throws Exception{
        boolean isSuccess = false;
        String sql = "INSERT INTO tblBooks (bookID, bookTitle, description, author, price,"
                + " quantity, image, status, categoryID, importDate)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, book.getBookID());
            preStm.setString(2, book.getBookTitle());
            preStm.setString(3, book.getDescription());
            preStm.setString(4, book.getAuthor());
            preStm.setLong(5, book.getPrice());
            preStm.setInt(6, book.getQuantity());
            preStm.setString(7, book.getImage());
            preStm.setBoolean(8, book.getStatus());
            preStm.setInt(9, book.getCategoryID());
            preStm.setDate(10, book.getImportDate());
            preStm.executeUpdate();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return isSuccess;
    }
    
//    public ArrayList<BooksDTO> getAll() throws Exception{
//        ArrayList<BooksDTO> bookList = new ArrayList<>();
//        String sql = BASE_SELECT
//                + " WHERE tblBooks.status = ?"
//                + " AND tblBooks.quantity > ?";
//        try {
//            conn = DBUtilities.makeConnection();
//            preStm = conn.prepareStatement(sql);
//            preStm.setBoolean(1, true);
//            preStm.setInt(2, 0);
//            rs = preStm.executeQuery();
//            
//            while(rs.next()){
//            String bookID = rs.getString("bookID");
//            String bookTitle = rs.getString("bookTitle");
//            long price = rs.getLong("price");
//            int quantity = rs.getInt("quantity");
//            boolean status = rs.getBoolean("status");
//            Date importDate = rs.getDate("importDate");
//            String image = rs.getString("image");
//            String description = rs.getString("description");
//            String author = rs.getString("author");
//            String categoryName = rs.getString("categoryName");
//            BooksDTO book = new BooksDTO(bookID, bookTitle, price, quantity, status, importDate, image, description, author, categoryName);
//            bookList.add(book);
//        }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally{
//            closeConnection();
//        }
//        return bookList;
//    }
    
    public BooksDTO getBookByID(String bookID)throws Exception{
        BooksDTO book = new BooksDTO();
        String sql = BASE_SELECT
                + " WHERE tblBooks.bookID = ?"
                + " AND tblBooks.status = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            preStm.setBoolean(2, true);
            rs = preStm.executeQuery();
            
            while(rs.next()){
            String rBookID = rs.getString("bookID");
            String bookTitle = rs.getString("bookTitle");
            long price = rs.getLong("price");
            int quantity = rs.getInt("quantity");
            boolean status = rs.getBoolean("status");
            Date importDate = rs.getDate("importDate");
            String image = rs.getString("image");
            String description = rs.getString("description");
            String author = rs.getString("author");
            String categoryName = rs.getString("categoryName");
            int categoryID = rs.getInt("categoryID");
            book = new BooksDTO(rBookID, bookTitle, price, quantity, status, importDate, image, description, author, categoryName, categoryID);
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return book;
    }
    
    public ArrayList<BooksDTO> getBooksByParams(String title, Integer categoryID, Long priceFrom, Long priceTo)throws Exception{
        ArrayList<BooksDTO> bookList = new ArrayList<>();
        String sql = BASE_SELECT
                + " WHERE tblBooks.status = ?";
        sql += " AND bookTitle LIKE ?";
        sql += " AND price >= ?";
        sql += " AND price <= ?";
        if(categoryID != 0){
            sql += " AND tblBooks.categoryID = ?";
        }
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, true);
            preStm.setString(2,"%" + title + "%");
            if(priceFrom != null){
                preStm.setLong(3, priceFrom);
            } else preStm.setInt(3, 0);
            if(priceTo != null){
                preStm.setLong(4, priceTo);
            } else preStm.setInt(4, Integer.MAX_VALUE);
            if(categoryID != 0){
                preStm.setInt(5, categoryID);
            }
            rs = preStm.executeQuery();
            while(rs.next()){
            String bookID = rs.getString("bookID");
            String bookTitle = rs.getString("bookTitle");
            long price = rs.getLong("price");
            int quantity = rs.getInt("quantity");
            boolean status = rs.getBoolean("status");
            Date importDate = rs.getDate("importDate");
            String image = rs.getString("image");
            String description = rs.getString("description");
            String author = rs.getString("author");
            String categoryName = rs.getString("categoryName");
            BooksDTO book = new BooksDTO(bookID, bookTitle, price, quantity, status, importDate, image, description, author, categoryName);
            bookList.add(book);
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return bookList;
    }
    
//    public int getQuantity(String bookID) throws Exception{
//        int quantity = 0;
//        String sql = "SELECT quantity FROM tblBooks"
//                + " WHERE bookID = ?";
//        try{
//            conn = DBUtilities.makeConnection();
//            preStm = conn.prepareStatement(bookID);
//            preStm.setString(1, bookID);
//            rs = preStm.executeQuery();
//            quantity = rs.getInt("quantity");
//        } catch(Exception e){
//            e.printStackTrace();
//        } finally{
//            closeConnection();
//        }
//        return quantity;
//    }
    
    public Boolean updateQuantity(String bookID, int quantity)throws Exception{
        Boolean result = false;
        String sql = "UPDATE tblBooks SET"
                + " quantity = ?"
                + " WHERE bookID = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setString(2, bookID);
            preStm.executeUpdate();
            result = true;
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
        return result;
    }
    

    public Boolean update(BooksDTO book)throws Exception{
        Boolean result = false;
        String sql = "UPDATE tblBooks SET"
                + " bookTitle = ?,"
                + " price = ?,"
                + " quantity = ?,"
                + " [image] = ?,"
                + " description = ?,"
                + " author = ?,"
                + " categoryID = ?"
                + " WHERE bookID = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, book.getBookTitle());
            preStm.setLong(2, book.getPrice());
            preStm.setInt(3, book.getQuantity());
            preStm.setString(4, book.getImage());
            preStm.setString(5, book.getDescription());
            preStm.setString(6, book.getAuthor());
            preStm.setInt(7, book.getCategoryID());
            preStm.setString(8, book.getBookID());
            System.out.println(preStm);
            int test = preStm.executeUpdate();
            System.out.println("<<<<<<"+test);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally{
            closeConnection();
        }
        return result;
    }
    
    public Boolean delete(String bookID)throws Exception{
        Boolean result = false;
        String sql = "UPDATE tblBooks SET status = 0 WHERE bookID = ?";
        try {
            conn = DBUtilities.makeConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            preStm.executeUpdate();
            result = true;
        } catch (Exception e) {
        } finally{
            closeConnection();
        }
        return result;
    }
}
