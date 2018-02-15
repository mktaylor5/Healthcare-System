
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1><%= session.getAttribute("name") %></h1>
        Date: <%= session.getAttribute("date") %><br>
        <form action="nurseedittreatmentcontent.jsp">
            Reason for visit: <%= session.getAttribute("re") %><br>
            Weight: <%= session.getAttribute("wt") %><br>
            Height: <%= session.getAttribute("ht") %><br>
            Blood Pressure: <%= session.getAttribute("bp") %><br>
            Treatment content: <%= session.getAttribute("tc") %><br>
            Prescription: <%= session.getAttribute("pr") %><br>
            <input type="Submit" value="Edit">
        </form>
        <br> 
        <a href="nursetreatmentcontent.jsp">View another treatment record</a>
    </body>
</html>
