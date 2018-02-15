<%-- 
    Document   : nurseedittreatmentcontent
    Created on : May 8, 2017, 5:01:00 PM
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
        <h1>Treatment Record</h1>
            Patient Name: <%= session.getAttribute("name") %><br>
            Date: <%= session.getAttribute("date") %><br>
        <form action="nurseeditsubmitted" method="post">
            Reason for visit: <input type="text" name="re"><br>
            Weight: <input type="text" name="wt"><br>
            Height: <input type="text" name="ht"><br>
            Blood Pressure: <input type="text" name="bp"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
