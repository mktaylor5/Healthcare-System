
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Daily report for <%= session.getAttribute("date") %></h1>
        <h3>Doctor 1</h3>
        No. of Patients: <%= session.getAttribute("patient1") %><br>
        Total Income: <%= session.getAttribute("income1") %><br>
        <h3>Doctor 2</h3>
        No. of Patients: <%= session.getAttribute("patient2") %><br>
        Total Income: <%= session.getAttribute("income1") %><br>
        <h3>Doctor 3</h3>
        No. of Patients: <%= session.getAttribute("patient3") %><br>
        Total Income: <%= session.getAttribute("income1") %><br>
        <h3>Doctor 4</h3>
        No. of Patients: <%= session.getAttribute("patient4") %><br>
        Total Income: <%= session.getAttribute("income1") %><br>
        <h3>Doctor 5</h3>
        No. of Patients: <%= session.getAttribute("patient5") %><br>
        Total Income: <%= session.getAttribute("income1") %><br>
        
    </body>
</html>
