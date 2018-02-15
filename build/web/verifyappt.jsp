
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Verify appointment</h1>
        <%= session.getAttribute("patientName") %> is scheduled for <%= session.getAttribute("date") %> 
        at <%= session.getAttribute("time") %> with <%= session.getAttribute("doctor") %><br>
        
        <a href="./changeappointment.jsp">Change appointment</a><br>
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
