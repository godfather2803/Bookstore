<%-- 
    Document   : cart
    Created on : Dec 10, 2020, 11:43:57 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="root/css/style.css">
        <title>Cart</title>
    </head>
    <body>
        <a href="BooksController">HOME</a>
        <c:set var="userCart" value="${USER_ID}Cart"/>
        <c:if test="${ROLE != 'CUSTOMER'}">
            <c:redirect url="BooksController"/>
        </c:if>
        <c:choose>
            <c:when test="${not empty sessionScope[userCart]}">
                <table>
                    <thead>
                        <tr>
                            <th>Book ID</th>
                            <th>Book Title</th>
                            <th>Quantity</th>
                            <th>Unit Price</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="total" value="0"/>
                        <c:forEach items="${sessionScope[userCart]}" var="item">
                            <tr>
                                <form action="MainController">
                                    <td>${item.getBookID()}</td>
                                    <td>${item.getBookTitle()}</td>
                                    <td>
                                        <input type="hidden" name="txtBookID" value="${item.getBookID()}">
                                        <input type="number" name="txtQuantity" min="1" value="${item.getQuantity()}">
                                        <input type="submit" name="btnAction" value="Update cart">
                                        <input type="submit" name="btnAction" value="Remove from cart">
                                    </td>
                                    <td>${item.getPrice()}</td>
                                </form>
                            </tr>
                            <c:set var="total" value="${total + (item.getPrice()*item.getQuantity())}" />
                        </c:forEach>
                    </tbody>
                </table>
                        <div style="width: 100%;display: flex; margin: auto; margin-top: 24px;">
                            <c:if test="${empty discountCode}">
                                <div style="width: 50%;">
                                <form action="MainController" method="POST">
                                    <input type="text" name="txtDiscountCode" value="">
                                    <input type="submit" name="btnAction" value="Apply discount code">
                                </form>
                                </div>
                            </c:if>
                            
                            <div style="width: 50%; text-align: end;">
                                <form action="MainController" method="POST">
                                <c:choose>
                                    <c:when test="${not empty discountCode}">
                                        <input type="hidden" name="appliedDiscountCode" value="${discountCode.getDiscountID()}">
                                        <input type="hidden" name="appliedPercent" value="${discountCode.getPercent()}">
                                        <h2>${discountCode.getPercent()}</h2>
                                        <b>Total: </b>${total / 100 * (100 - discountCode.getPercent())}
                                        <input type="hidden" name="txtTotalPrice" value="${total}">
                                    </c:when>
                                    <c:when test="${empty discountCode}">
                                        <b>Total: </b>${total}
                                        <input type="hidden" name="txtTotalPrice" value="${total}">
                                    </c:when>
                                </c:choose>
                                        <input type="submit" name="btnAction" value="Purchase">
                                </form>
                            </div>
                            <div>
                                    
                            </div>
                        </div>
                        
                        
            </c:when>
            <c:when test="${empty sessionScope[userCart]}">
                <h2>Your cart is empty !! let's pick up some knowledge <3</h2>
            </c:when>
        </c:choose>
    </body>
</html>
