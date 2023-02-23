<%-- 
    Document   : login
    Created on : Dec 10, 2020, 11:43:08 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="root/css/style.css">
    </head>
    <body>
    <c:if test="${not empty ROLE}">
        <c:redirect url="MainController"/>
    </c:if>
        <div id="loginForm" class="main">
            <form method="POST" action="MainController">
                <input type="text" name="txtUserID" placeholder="Username"></br>
                <input type="password" name="txtPassword" placeholder="Password"></br>
                <input type="submit" name="btnAction" value="Login">
            </form>
        </div>
    </body>
</html>
