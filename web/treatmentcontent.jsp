<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Treatment Record</h1>
        <form action="viewtreatmentcontent" method="get">
            <!-- didn't work
            //< %
            //    ArrayList patients = (ArrayList)session.getAttribute("patientList");
            //        for (int i=0; i < patients.size(); i++){
            //           out.println(patients.get(i));
            //}% >
            -->
            Patient Name: <input type="text" name="name"><br>
            <input type="submit" value="Submit">
        </form><br>         
    </body>
</html>