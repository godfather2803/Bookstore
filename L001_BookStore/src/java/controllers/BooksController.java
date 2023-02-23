/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.BooksDAO;
import daos.CategoriesDAO;
import dtos.BooksDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import define.Define;
import dtos.CategoriesDTO;
import java.io.File;
import java.util.Date;
import java.util.UUID;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author DELL
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class BooksController extends HttpServlet {

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
        String url = Define.INDEX_PAGE;
        String action = request.getParameter("btnAction");
        BooksDAO booksDAO = new BooksDAO();
        try {
            if (action != null) {
                String bookID = request.getParameter("txtBookID");
                if (action.equalsIgnoreCase("Delete")) {
                    boolean isSuccess = booksDAO.delete(bookID);
                    url = Define.INDEX_PAGE;
                } else if (action.equalsIgnoreCase("Detail")) {
                    BooksDTO book = booksDAO.getBookByID(bookID);
                    request.setAttribute("BOOK_DETAIL", book);
                    url = Define.BOOK_DETAIL_PAGE;
                } else if (action.equalsIgnoreCase("Insert book")) {
                    CategoriesDAO categoriesDAO = new CategoriesDAO();
                    ArrayList<CategoriesDTO> listCategory = categoriesDAO.getAll();
                    request.setAttribute("CATEGORY_LIST", listCategory);
                    url = Define.INSERT_EDIT_PAGE;
                } else if (action.equalsIgnoreCase("Edit book")) {
                    BooksDTO book = booksDAO.getBookByID(bookID);
                    request.setAttribute("BOOK_DETAIL", book);
                    CategoriesDAO categoriesDAO = new CategoriesDAO();
                    ArrayList<CategoriesDTO> listCategory = categoriesDAO.getAll();
                    request.setAttribute("CATEGORY_LIST", listCategory);
                    url = Define.INSERT_EDIT_PAGE;
                } else if (action.equalsIgnoreCase("Insert") || action.equalsIgnoreCase("Edit")) {
                    if (action.equalsIgnoreCase("Insert")) {
                        boolean isExist = booksDAO.checkBookExist(bookID);
                        if (isExist) {
                            request.setAttribute("INSERT_MESSAGE", "Book ID is existed");
                            url = Define.INSERT_EDIT_PAGE;
                            return;
                        }
                    }
                    boolean result = false;
                    String txtBookTitle = request.getParameter("txtBookTitle");
                    String txtDescription = request.getParameter("txtDescription");
                    String txtAuthor = request.getParameter("txtAuthor");
                    Integer selectCategory = Integer.parseInt(request.getParameter("selectCategory"));
                    Long txtPrice = Long.parseLong(request.getParameter("txtPrice"));
                    Integer txtQuantity = Integer.parseInt(request.getParameter("txtQuantity"));
                    Date date = new Date();
                    java.sql.Date now = new java.sql.Date(date.getTime());
                    String directoryPath = this.getServletContext().getRealPath("")+ "/images";
                    // create new directory if needed
                    File directory = new File(directoryPath);
                    if (!directory.exists()) {
                        boolean directoryCreated = directory.mkdirs();

                        if (!directoryCreated) {
                            throw new IllegalStateException("Cannot create directory to save file");
                        }
                    }

                    // get the file
                    Part image = request.getPart("inputImage");
                    
                    // prepare file path
                    String filePath = directoryPath + "/" + this.getFileName(image);
                    
                    // and save file
                    image.write(filePath);
                    // 
                    BooksDTO book = new BooksDTO(bookID, txtBookTitle, txtPrice, txtQuantity, Boolean.TRUE, now, "images/" + this.getFileName(image), txtDescription, txtAuthor, selectCategory);
                    System.out.println("BOOK DETAIL " + book.toString());
                    if (action.equalsIgnoreCase("Edit")) {
                        result = booksDAO.update(book);
                    } else {
                        result = booksDAO.insert(book);
                    }
                    BooksDTO newBook = booksDAO.getBookByID(bookID);
                    request.setAttribute("BOOK_DETAIL", newBook);
                    url = Define.BOOK_DETAIL_PAGE;
                }
            }

            if (url.equalsIgnoreCase(Define.INDEX_PAGE)) {
                String txtTitle = request.getParameter("txtTitle");
                String selectCategory = request.getParameter("selectCategory");
                String txtPriceFrom = request.getParameter("txtPriceFrom");
                String txtPriceTo = request.getParameter("txtPriceTo");
                String title = "";
                int categoryID = 0;
                Long priceFrom = null;
                Long priceTo = null;

                CategoriesDAO categoriesDAO = new CategoriesDAO();
                ArrayList<BooksDTO> bookList;
                ArrayList<CategoriesDTO> categoryList;

                if (txtTitle != null) {
                    if (!txtTitle.isEmpty()) {
                        request.setAttribute("txtTitle", txtTitle);
                        title = txtTitle;
                    }
                }
                if (selectCategory != null) {
                    request.setAttribute("selectedCategory", selectCategory);
                    categoryID = Integer.parseInt(selectCategory);
                }
                if (txtPriceFrom != null) {
                    if (!txtPriceFrom.isEmpty()) {
                        priceFrom = Long.parseLong(txtPriceFrom);
                        request.setAttribute("txtPriceFrom", txtPriceFrom);
                    }
                }
                if (txtPriceTo != null) {
                    if (!txtPriceTo.isEmpty()) {
                        priceTo = Long.parseLong(txtPriceTo);
                        request.setAttribute("txtPriceTo", txtPriceTo);
                    }
                }
                categoryList = categoriesDAO.getAll();
                bookList = booksDAO.getBooksByParams(title, categoryID, priceFrom, priceTo);
                request.setAttribute("BOOK_LIST", bookList);
                request.setAttribute("CATEGORY_LIST", categoryList);
            }
        } catch (Exception e) {
            url = Define.ERROR_PAGE;
            request.setAttribute("error", e.getLocalizedMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    private String getFileName(Part part) {
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename"))
            return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
    return UUID.randomUUID().toString() + ".jpg";
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
