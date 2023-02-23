<%-- 
    Document   : manage_user
    Created on : Dec 18, 2020, 2:36:44 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All User</title>
    </head>
    <body>
        <style>
            #table-user{
                border: 1px solid black;
                border-collapse: collapse;
            }
        </style>
        <h1>USER</h1>
        <table id="table-user">
            <thead>
                <tr>
                    <th>UserID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${LIST_USER}" var="user">
                    <tr>
                        <td>${user.userID}</td>
                        <td>${user.name}</td>
                        <td>${user.address}</td>
                        <td>${user.phone}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
