<%-- 
    Document   : monthlyreport
    Created on : May 3, 2017, 4:00:17 PM
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
        <h1>Monthly Report</h1>
        <!-- Make monthly report servlet named monthlyreport.java with actions in post method -->
        <form action="monthlyreport" action="post">
            Select the month you would like to view: 
            <select name="month">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <option value="4">April</option>
                <option value="5">May</option>
                <option value="6">June</option>
                <option value="7">July</option>
                <option value="8">August</option>
                <option value="9">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select>
            <input type="submit" value="Submit">
        </form>

    </body>
</html>