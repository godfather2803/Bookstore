/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UsersDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import define.Define;
import dtos.UsersDTO;
import javax.servlet.http.HttpSession;
/**
 *
 * @author DELL
 */
public class UsersController extends HttpServlet {

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
        String url = Define.LOGIN_PAGE;
        String action = request.getParameter("btnAction");
        HttpSession session = request.getSession();
        try {
            if(action != null){
                if(action.equalsIgnoreCase("Login")){
                    String username = request.getParameter("txtUserID");
                    String password = request.getParameter("txtPassword");
                    UsersDTO user = new UsersDTO(username, password);
                    UsersDAO usersDAO = new UsersDAO();
                    String role = usersDAO.checkLogin(user);
                    if(role != null && !role.isEmpty()){
                        url = Define.BOOKS_CONTROLLER;
                        session.setAttribute("USER_ID", username);
                        session.setAttribute("ROLE", role);
                    }
                }
                else if(action.equalsIgnoreCase("Logout")){
                    session.removeAttribute("USER_ID");
                    session.removeAttribute("ROLE");
                    url = Define.BOOKS_CONTROLLER;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            request.setAttribute("error", e.getLocalizedMessage());
            url = Define.ERROR_PAGE;
        }finally{
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
