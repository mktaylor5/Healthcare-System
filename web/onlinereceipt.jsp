<%-- 
    Document   : onlinereceipt
    Created on : May 9, 2017, 1:35:35 AM
    Author     : Meagn-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Online Payment Receipt</h1>
        Invoice Number: <%= session.getAttribute("invoice") %><br>
        Patient: <%= session.getAttribute("name") %><br>
        Payment Amount: <%= session.getAttribute("amount") %><br>
    </body>
</html>
