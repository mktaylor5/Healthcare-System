
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Check in page for existing patient</h1>
        <form action="checkin" method="post">
        Patient Name:       <input type="text" name="patientName">
        Doctor:
        <select name="doctor">
            <option value="doc1">Doctor 1</option>
            <option value="doc2">Doctor 2</option>
            <option value="doc3">Doctor 3</option>
            <option value="doc4">Doctor 4</option>
            <option value="doc5">Doctor 5</option>
        </select><br>
        Appointment Time:
        <select name="hour">
            <!--Two drop downs: 9,10,11,12,1,2,3,4 and 00, 30-->
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
        </select>
        :
        <select name="minutes">
            <option value="00">00</option>
            <option value="30">30</option>
        </select>
        <select name="am_pm">
            <option value="am">A.M.</option>
            <option value="pm">P.M.</option>
        </select><br>
        <input type="submit" value="Submit">
        </form>
        <br>
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
