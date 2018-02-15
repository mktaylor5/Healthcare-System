<%-- 
    Document   : viewmonthlyreport
    Created on : May 9, 2017, 10:00:12 AM
    Author     : Meagn-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Monthly Report for <%= session.getAttribute("month") %></h1>
        <h3>Doctor 1</h3>
        Total Patients: <%= session.getAttribute("p1") %><br>
        Total Income: <%= session.getAttribute("i1") %><br>
        
        <h3>Doctor 2</h3>
        Total Patients: <%= session.getAttribute("p2") %><br>
        Total Income: <%= session.getAttribute("i2") %><br>
        
        <h3>Doctor 3</h3>
        Total Patients: <%= session.getAttribute("p3") %><br>
        Total Income: <%= session.getAttribute("i3") %><br>
        
        <h3>Doctor 4</h3>
        Total Patients: <%= session.getAttribute("p4") %><br>
        Total Income: <%= session.getAttribute("i4") %><br>
        
        <h3>Doctor 5</h3>
        Total Patients: <%= session.getAttribute("p5") %><br>
        Total Income: <%= session.getAttribute("i5") %><br>
        
    </body>
</html>
