
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Receipt for <%= session.getAttribute("name") %></h1>
        Payment Type: <%= session.getAttribute("type") %><br>
        Amount: $<%= session.getAttribute("amount") %><br>
        Email: <%= session.getAttribute("email") %><br>
        
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
