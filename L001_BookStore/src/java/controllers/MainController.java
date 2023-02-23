/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import define.Define;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
@MultipartConfig
public class MainController extends HttpServlet {
    

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
        String url = Define.BOOKS_CONTROLLER;
        HttpSession session = request.getSession();
        String action = request.getParameter("btnAction");
        try {
            if (action != null) {
                if (action.equalsIgnoreCase("Login") || action.equalsIgnoreCase("Logout")) {
                    url = Define.USERS_CONTROLLER;
                } else if (action.equalsIgnoreCase("Insert book")
                        || action.equalsIgnoreCase("Edit book")
                        || action.equalsIgnoreCase("Insert")
                        || action.equalsIgnoreCase("Edit")
                        || action.equalsIgnoreCase("Search")
                        || action.equalsIgnoreCase("Detail")
                        || action.equalsIgnoreCase("Delete")) {
                    url = Define.BOOKS_CONTROLLER;
                } else if (action.startsWith("View cart")
                        || action.equalsIgnoreCase("Add to cart")
                        || action.equalsIgnoreCase("Remove from cart")
                        || action.equalsIgnoreCase("Update cart")
                        || action.equalsIgnoreCase("Apply discount code")
                        || action.equalsIgnoreCase("Purchase")) {
                    url = Define.CARTS_CONTROLLER;
                } else if(action.equalsIgnoreCase("Insert discount code")){
                    url = Define.DISCOUNT_CODES_CONTROLLER;
                }
                else {
                    url = Define.ERROR_PAGE;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getLocalizedMessage());
            url = Define.ERROR_PAGE;
        } finally {
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
