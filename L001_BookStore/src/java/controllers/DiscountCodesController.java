/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.DiscountCodesDAO;
import dtos.DiscountCodesDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class DiscountCodesController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        String action = request.getParameter("btnAction");
        HttpSession session = request.getSession();
        try {
            if(action != null){
                if(action.equalsIgnoreCase("Insert discount code")){
                    String txtDiscountID = request.getParameter("txtDiscountID");
                    String txtPercent = request.getParameter("txtPercent");
                    int percent = Integer.parseInt(txtPercent);
                    String txtDate = request.getParameter("txtDate");
                    DiscountCodesDAO discountCodesDAO = new DiscountCodesDAO();
                    DiscountCodesDTO discountCode = new DiscountCodesDTO(txtDiscountID, url, percent, null, Boolean.FALSE);
                    boolean isSuccess = discountCodesDAO.insert(discountCode);
                    if(isSuccess){
                        url = define.Define.INSERT_DISCOUNT_PAGE;
                        request.setAttribute("INSERT_MESSAGE", "Insert success");
                    } else{
                        url = define.Define.INSERT_DISCOUNT_PAGE;
                        request.setAttribute("INSERT_MESSAGE", "Insert fail");
                    }
                }
            }
            
        } catch (Exception e){
            
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
