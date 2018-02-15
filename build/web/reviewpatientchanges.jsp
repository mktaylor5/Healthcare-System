
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Patient Information Updated</h1>
            Patient Name: <%= session.getAttribute("name") %><br>
            Address: <%= session.getAttribute("address") %><br>
            Phone: <%= session.getAttribute("phone") %><br>
            Email: <%= session.getAttribute("email") %><br>
            Insurance: <%= session.getAttribute("insurance") %><br>
            <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
