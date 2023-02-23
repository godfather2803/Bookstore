<%-- 
    Document   : index
    Created on : Dec 8, 2020, 3:24:18 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book store</title>
        <link rel="stylesheet" href="root/css/style.css">
    </head>
    <body>
        <c:set var="context" value="${pageContext.request.contextPath}" />
        <c:set var="userCart" value="${USER_ID}Cart"/>

        <style>
            .roleActionDiv{
                margin-top: 24px;
                display: flex;
                justify-content: center;
            }
            .roleActionDiv div{
                min-width: 33%;
                display: flex;
                justify-content: center
            }
        </style>
        <div class="main">
            <div id="searchDiv">
                <form action="MainController" method="POST">
                    <div>
                        <input type="text" name="txtTitle" value="${txtTitle}" placeholder="Input somthing to search">
                        <select name="selectCategory">
                            <option value="0"${selectedCategory == '0' ? 'selected' : ''}>-ALL-</option>
                            <c:if test="${not empty CATEGORY_LIST}">
                                <c:forEach items="${CATEGORY_LIST}" var="category">
                                    <option value="${category.getCategoryID()}" ${category.getCategoryID() == selectedCategory ? 'selected' : ''}>${category.getCategoryName()}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <input type="submit" value="Search" name="btnAction">
                    </div>
                    <div>
                        <input name="txtPriceFrom" value="${txtPriceFrom}" type="number" min="0" id="txtFromPrice" placeholder="From price">
                        <input name="txtPriceTo"value="${txtPriceTo}" type="number" min="0" id="txtToPrice" placeholder="To price">
                    </div>
                </form>
            </div>
            <div class="roleActionDiv">
                <div>
                    <a href="GetAllUserController">ALL USER</a>
                </div>
                <c:choose>
                    <c:when test="${empty ROLE}">
                        <div>
                            <a href="login.jsp">Login</a>
                        </div>
                    </c:when>
                    <c:when test="${not empty ROLE}">
                        <div>
                            <form action="MainController" method="POST">
                                <input type="submit" name="btnAction" value="Logout">
                            </form>
                        </div>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${ROLE == 'ADMIN'}">
                        <div>
                            <form method="POST" action="MainController">
                                <input type="submit" name="btnAction" value="Insert book">
                            </form>
                        </div>
                        <div>
                            <a href="insert_discount_code.jsp">CREATE DISCOUNT CODE</a>
                        </div>
                    </c:when>
                    <c:when test = "${ROLE == 'CUSTOMER'}">
                        <div>
                            <a href="shopping_history.jsp">SHOPPING HISTORY</a>
                        </div>
                        <div>
                            <form action="MainController" method="POST">
                                <input type="submit" name="btnAction" value="View cart (${sessionScope[userCart].size()})">
                            </form>
                        </div>
                    </c:when>
                </c:choose>
            </div>
            <c:if test="${not empty BOOK_LIST}">
                <div id="resultDiv">
                    <table>
                        <thead>
                            <tr>
                                <th>Image</th>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Author</th>
                                <th>Price</th>
                                <th>Category</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${BOOK_LIST}" var="book">
                                <tr>
                                    <td><img src="${context}/${book.image}" alt="hinh" width="300" height="200"></td>
                                    <td>${book.getBookTitle()}</td>
                                    <td>${book.getDescription()}</td>
                                    <td>${book.getAuthor()}</td>
                                    <td>${book.getPrice()}</td>
                                    <td>${book.getCategoryName()}</td>
                                    <td>
                                        <form method="POST" action="MainController">
                                            <input type="hidden" name="txtBookID" value="${book.getBookID()}">
                                            <input type="hidden" name="txtBookTitle" value="${book.getBookTitle()}">
                                            <input type="hidden" name="txtPrice" value="${book.getPrice()}">
                                            <input type="submit" name="btnAction" value="Detail">
                                            <c:if test="${ROLE == 'CUSTOMER'}">
                                                <input type="submit" name="btnAction" value="Add to cart">
                                            </c:if>

                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
    </body>
</html>
