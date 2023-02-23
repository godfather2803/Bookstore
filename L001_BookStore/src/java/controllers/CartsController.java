/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BooksDAO;
import daos.DiscountCodesDAO;
import daos.OrdersDAO;
import dtos.CartItemsDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import define.Define;
import dtos.BooksDTO;
import dtos.DiscountCodesDTO;
import dtos.OrdersDTO;
import java.util.Date;

/**
 *
 * @author DELL
 */
@SuppressWarnings("unchecked")
public class CartsController extends HttpServlet {
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        String action = request.getParameter("btnAction");
        HttpSession session = request.getSession();
        try {
            if(action != null){
                if(action.equalsIgnoreCase("Add to cart")){
                    String bookID = request.getParameter("txtBookID");
                    String bookTitle = request.getParameter("txtBookTitle");
                    Long price = Long.parseLong(request.getParameter("txtPrice"));
                    String userID = (String)session.getAttribute("USER_ID");
                    ArrayList<CartItemsDTO> cart = (ArrayList<CartItemsDTO>)
                            session.getAttribute(userID + "Cart");
                    if(cart != null){
                        boolean isNew = true;
                        for (CartItemsDTO item : cart) {
                            if(item.getBookID().equals(bookID)){
                                item.setQuantity((item.getQuantity() + 1));
                                isNew = false;
                                break;
                            }
                        }
                        if(isNew){
                            CartItemsDTO newCartItem = new CartItemsDTO(bookID, bookTitle, 1, price);
                            cart.add(newCartItem);
                        }
                    } else {
                        cart = new ArrayList<>();
                        CartItemsDTO newCartItem = new CartItemsDTO(bookID, bookTitle, 1, price);
                        cart.add(newCartItem);
                    }
                    
                    if(request.getParameter("txtDetailAddToCart") != null){
                        BooksDAO booksDAO = new BooksDAO();
                        BooksDTO book = booksDAO.getBookByID(bookID);
                        request.setAttribute("BOOK_DETAIL", book);
                        url = Define.BOOK_DETAIL_PAGE;
                    } else{
                        url = Define.BOOKS_CONTROLLER;
                    }
                    System.out.println(userID+"Cart");
                    session.setAttribute(userID+"Cart", cart);
                } else if(action.startsWith("View cart")){
                    url = Define.CART_PAGE;
                } else if(action.equals("Update cart")){
                    String bookID = request.getParameter("txtBookID");
                    int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
                    String userID = (String) session.getAttribute("USER_ID");
                    ArrayList<CartItemsDTO> cart =(ArrayList<CartItemsDTO>) session.getAttribute(userID + "Cart");
                    for (CartItemsDTO item : cart) {
                        if(item.getBookID().equals(bookID)){
                            item.setQuantity(quantity);
                            break;
                        }
                    }
                    session.setAttribute("cart", cart);
                    url = Define.CART_PAGE;
                } else if(action.equals("Remove from cart")){
                    String bookID = request.getParameter("txtBookID");
                    String userID = (String) session.getAttribute("USER_ID");
                    ArrayList<CartItemsDTO> cart =(ArrayList<CartItemsDTO>) session.getAttribute(userID + "Cart");
                    for (CartItemsDTO item : cart) {
                        if(item.getBookID().equals(bookID)){
                            cart.remove(item);
                            break;
                        }
                    }
                    session.setAttribute("cart", cart);
                    url = Define.CART_PAGE;
                } else if(action.equals("Apply discount code")){
                    String discountID = request.getParameter("txtDiscountCode");
                    String userID = (String)session.getAttribute("USER_ID");
                    Date date = new Date();
                    java.sql.Date currentDate = new java.sql.Date(date.getTime());
                    DiscountCodesDAO discountCodesDAO = new DiscountCodesDAO();
                    DiscountCodesDTO discountCodes = discountCodesDAO.getAvailableDiscountCode(userID, discountID, currentDate);
                    if(discountCodes.getDiscountID() != null){
                        request.setAttribute("discountCode", discountCodes);
                    }
                    url = Define.CART_PAGE;
                } else if(action.equalsIgnoreCase("Purchase")){
                    String appliedDiscountCode = request.getParameter("appliedDiscountCode");
                    String appliedPercent = request.getParameter("appliedPercent");
                    Long totalPrice = Long.parseLong(request.getParameter("txtTotalPrice"));
                    if(appliedPercent != null){
                        totalPrice = totalPrice / 100 * (100 - Integer.parseInt(appliedPercent));
                    }
                    String userID = (String)session.getAttribute("USER_ID");
                    long millis=System.currentTimeMillis();
                    java.sql.Date currentDate = new java.sql.Date(millis);
                    OrdersDAO ordersDAO = new OrdersDAO();
                    OrdersDTO order = new OrdersDTO(userID, totalPrice, currentDate, appliedDiscountCode);
                    boolean isSuccess = ordersDAO.insert(order);
                    if(isSuccess){
                        session.removeAttribute(userID+"Cart");
                        if(appliedDiscountCode != null){
                            DiscountCodesDAO discountCodeDAO = new DiscountCodesDAO();
                            boolean isUsed = discountCodeDAO.useDiscountCode(appliedDiscountCode);
                        }
                        url = Define.BOOKS_CONTROLLER;
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", e.getLocalizedMessage());
            url = Define.ERROR_PAGE;
        } finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
