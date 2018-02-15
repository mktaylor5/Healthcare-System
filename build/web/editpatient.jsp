
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Patient Information Record</h1>
            Patient Name: <%= session.getAttribute("name") %><br>
        <form action="editpatient" method="post">
            Address: <input type="text" name="address"><br>
            Phone: <input type="text" name="phone"><br>
            Email: <input type="text" name="email"><br>
            Insurance: <input type="text" name="insurance"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
