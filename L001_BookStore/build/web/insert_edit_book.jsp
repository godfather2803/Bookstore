    <%-- 
    Document   : insert_edit_book
    Created on : Dec 10, 2020, 11:45:11 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book management</title>
        <link rel="stylesheet" href="root/css/style.css">
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    </head>
    <body>
        <h3>${INSERT_MESSAGE}</h3>
        <c:if test="${ROLE != 'ADMIN'}">
            <c:redirect url="BooksController"/>
        </c:if>
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <a href="BooksController">HOME</a>
        <div id="editBookForm" class="main">
            <form method="POST" action="MainController" enctype="multipart/form-data">
                    <c:if test="${not empty BOOK_DETAIL}">
                        <c:set var="readonly" value="readonly='readonly'"/>
                    </c:if>
                <p>Book ID</p>
                <input type="text" name="txtBookID" placeholder="ID" value="${BOOK_DETAIL.bookID}" ${readonly}>
                <p>Book Title</p>
                <input type="text" name="txtBookTitle" placeholder="Title" value="${BOOK_DETAIL.bookTitle}">
                <p>Description</p>
                <textarea placeholder="Description" name="txtDescription">${BOOK_DETAIL.description}</textarea>
                <p>Author</p>
                <input type="text" name="txtAuthor" placeholder="Author" value="${BOOK_DETAIL.author}">
                <p>Category</p>
                <select name="selectCategory">
                    <c:if test="${not empty CATEGORY_LIST}">
                        <c:forEach items="${CATEGORY_LIST}" var="category">
                            <option value="${category.getCategoryID()}" ${category.getCategoryID() == BOOK_DETAIL.categoryID ? 'selected' : ''}>${category.getCategoryName()}</option>
                        </c:forEach>
                    </c:if>
                </select>
                <p>Price</p>
                <input type="number" min="0" name="txtPrice"value="${BOOK_DETAIL.price}">
                <p>Quantity</p>
                <input type="number" min="0" name="txtQuantity" value="${BOOK_DETAIL.quantity}">
                <p>Image</p>

                <img src="${context}/${BOOK_DETAIL.image}" alt="hinh" width="300" height="200">

                <input type="file" name="inputImage">
                <br>
                <c:choose>
                    <c:when test="${not empty BOOK_DETAIL}">
                        <input type="submit" name="btnAction" value="Edit">
                    </c:when>
                    <c:when test="${empty BOOK_DETAIL}">
                        <input type="submit" name="btnAction" value="Insert">
                    </c:when>
                </c:choose>
                
            </form>
        </div>
    </body>
</html>
