
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Invoice for <%= session.getAttribute("name") %></h1>
        Invoice #: <%= session.getAttribute("invoiceNum") %><br>
        Date: <%= session.getAttribute("date") %><br>
        Service: <%= session.getAttribute("service") %><br>
        Cost: <%= session.getAttribute("cost") %><br>
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>