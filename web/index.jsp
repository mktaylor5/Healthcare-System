
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <center><h1>HCS</h1></center>
        <div align ="center">
        <form action="authorization" method="post">
            <br>Username: 
            <input type="text" name="username">
            <br>PIN: 
            <input type="text" name="pin"><br>
            <input type="submit" name="authorize" value="LOGIN"><br><br><br><br>
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

            
            <a href="./patienthome.jsp">Online Patient Portal</a>
        </form>
        </div>
    </body>
</html>
