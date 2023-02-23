<%-- 
    Document   : book_detail
    Created on : Dec 10, 2020, 11:43:28 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book detail</title>
        <link rel="stylesheet" href="root/css/style.css">
    </head>
    <body>
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <style>
            .small-label{
                margin: 0 !important;
                font-weight: bold;
            }
            .main{
                width: 90%;
            }
            .header{
                width: 100%;
                height: 50px;
            }
            .book-detail{
                width: 100%;
            }
            .book-detail p{
                margin-top: 0;
            }
            * {box-sizing: border-box}

            /* Set a style for all buttons */
            .cancelbtn, .deletebtn {
              background-color: #4CAF50;
              color: white;
              padding: 14px 20px;
              margin: 8px 0;
              border: none;
              cursor: pointer;
              opacity: 0.9;
            }

            #deleteModal button:hover, input:hover {
              opacity:1;
            }

            /* Float cancel and delete buttons and add an equal width */
            .cancelbtn, .deletebtn {
              float: left;
              width: 50%;
            }

            /* Add a color to the cancel button */
            .cancelbtn {
              background-color: #ccc;
              color: black;
            }

            /* Add a color to the delete button */
            .deletebtn {
              background-color: #f44336;
            }

            /* Add padding and center-align text to the container */
            .container {
              padding: 16px;
              text-align: center;
            }

            /* The Modal (background) */
            .modal {
              display: none; /* Hidden by default */
              position: fixed; /* Stay in place */
              z-index: 1; /* Sit on top */
              left: 0;
              top: 0;
              width: 100%; /* Full width */
              height: 100%; /* Full height */
              overflow: auto; /* Enable scroll if needed */
              background-color: #474e5d;
              padding-top: 50px;
            }

            /* Modal Content/Box */
            .modal-content {
              background-color: #fefefe;
              margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
              border: 1px solid #888;
              width: 80%; /* Could be more or less, depending on screen size */
            }

            /* Style the horizontal ruler */
            hr {
              border: 1px solid #f1f1f1;
              margin-bottom: 25px;
            }

            /* The Modal Close Button (x) */
            .close {
              position: absolute;
              right: 35px;
              top: 15px;
              font-size: 40px;
              font-weight: bold;
              color: #f1f1f1;
            }

            .close:hover,
            .close:focus {
              color: #f44336;
              cursor: pointer;
            }

            /* Clear floats */
            .clearfix::after {
              content: "";
              clear: both;
              display: table;
            }

            /* Change styles for cancel button and delete button on extra small screens */
            @media screen and (max-width: 300px) {
              .cancelbtn, .deletebtn {
                width: 100%;
              }
            }
        </style>
        <div class="main">
            <div class="header">
                <a href="BooksController">HOME</a>
                <h2 style="text-align: center;">Book Detail</h2>
            </div>
            
            <div class="book-detail">
                <p class="small-label">Book ID</p>
                <p>${BOOK_DETAIL.getBookID()}</p>
                <p class="small-label">Book Title</p>
                <p>${BOOK_DETAIL.getBookTitle()}</p>
                <p class="small-label">Description</p>
                <p>${BOOK_DETAIL.getDescription()}</p>
                <p class="small-label">Author</p>
                <p>${BOOK_DETAIL.getAuthor()}</p>
                <p class="small-label">Price</p>
                <p>${BOOK_DETAIL.getPrice()}</p>
                <p class="small-label">Quantity</p>
                <p>${BOOK_DETAIL.getQuantity()}</p>
                <p class="small-label">Import Date</p>
                <p>${BOOK_DETAIL.getImportDate()}</p>
                <p class="small-label">Category</p>
                <p>${BOOK_DETAIL.getCategoryName()}</p>
                <img src="${context}/${BOOK_DETAIL.image}" alt="hinh" width="300" height="200">
                <c:if test="${ROLE == 'CUSTOMER'}">
                    <form action="MainController" method="POST">
                        <input type="hidden" name="txtBookID" value="${BOOK_DETAIL.getBookID()}">
                        <input type="hidden" name="txtBookTitle" value="${BOOK_DETAIL.getBookTitle()}">
                        <input type="hidden" name="txtPrice" value="${BOOK_DETAIL.getPrice()}">
                        <input type="hidden" name="txtDetailAddToCart" value="Add to cart from detail">
                        <input type="submit" name="btnAction" value="Add to cart">
                    </form>
                </c:if>
                <c:if test="${ROLE == 'ADMIN'}">
                    <form action="MainController" method="POST">
                        <button type="button" onclick="document.getElementById('deleteModal').style.display='block'">Delete</button>
                        <input type="submit" name="btnAction" value="Edit book">
                        <div id="deleteModal" class="modal">
                            <span onclick="document.getElementById('deleteModal').style.display='none'" class="close" title="Close Modal">&times;</span>

                              <div class="container">
                                <h1>Delete Book</h1>
                                <p>Are you sure you want to delete book ${BOOK_DETAIL.getBookID()}</p>
                                <input type="hidden" name="txtBookID" value="${BOOK_DETAIL.getBookID()}">
                                <div class="clearfix">
                                  <button type="button" class="cancelbtn" onclick="document.getElementById('deleteModal').style.display='none'">Cancel</button>
                                  <input type="submit" class="deletebtn" name="btnAction" value="Delete">
                                </div>
                              </div>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </body>
</html>
