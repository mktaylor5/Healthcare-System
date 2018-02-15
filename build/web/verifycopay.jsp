
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Copay payment accepted!</h1>
        Patient Name: <%= session.getAttribute("name") %><br>
        Copay Amount: $<%= session.getAttribute("name") %>
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
