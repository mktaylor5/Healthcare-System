
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>You have added a new patient!</h1>
        Patient Name: <%= session.getAttribute("patient") %><br><br>
        
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
