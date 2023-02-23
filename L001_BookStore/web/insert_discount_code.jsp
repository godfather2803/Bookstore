<%-- 
    Document   : insert_discount_code.jsp
    Created on : Dec 12, 2020, 12:06:06 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${ROLE != 'ADMIN'}">
            <c:redirect url="BooksController"/>
        </c:if>
        <h2>Insert discount code</h2>
        <form action="MainController" method="POST">
            <input type="text" name="txtDiscountID" placeholder="Discount code">
            <input type="number" name="txtPercent" min="0" placeholder="Percent">
            <input type="date" name="txtDate" placeholder="Expired date">
            <input type="text" name="txtUserID" placeholder="User id">
            <input type="submit" name="btnAction" value="Insert discount code">
        </form>
    </body>
</html>
