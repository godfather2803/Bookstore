<%-- 
    Document   : error
    Created on : Dec 10, 2020, 8:33:24 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <a href="BooksController">HOME</a>
        <h1>${requestScope.error}</h1>
    </body>
</html>
