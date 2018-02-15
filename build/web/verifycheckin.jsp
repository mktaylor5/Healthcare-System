
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Patient check-in successful!</h1>
        Patient <%= session.getAttribute("name") %> has been checked in<br>
        Appointment Time: <%= session.getAttribute("time") %><br>
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
