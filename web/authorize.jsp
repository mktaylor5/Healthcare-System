<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Authorization Success!</h1>
        <%= session.getAttribute("user") %><br>
        <%= session.getAttribute("pinnum") %><br>
        <%= session.getAttribute("employeeType") %><br>
        <a href="./index.jsp">Back to Login</a>
    </body>
</html>
