
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Appointment canceled</h1>
        <%= session.getAttribute("name") %>'s appointment has been canceled<br>
        Date: <%= session.getAttribute("date") %><br>
        Time: <%= session.getAttribute("time") %><br>
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
