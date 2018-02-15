
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Staff Home Page</h1>
        <h3>Menu</h3>
        <h4>Appointments</h4>
        <ul>
            <li><a href="./makeappointment.jsp">Make Appointment</a></li>
            <li><a href="./changeappointment.jsp">Change Appointment</a></li>
            <li><a href="./cancelappointment.jsp">Cancel Appointment</a></li>
        </ul>
        
        <h4>Patients</h4>
        <ul>
            <li><a href="./newpatient.jsp">Add New Patient</a></li>
            <li><a href="./chooseeditpatient.jsp">Edit Patient Information</a></li>
            <!-- Only works on appointments on current day (real time) -->
            <li><a href="./checkin.jsp">Check-in Patient</a></li>
        </ul>
        
        <h4>Payment</h4>
        <ul>
            <li><a href="./invoiceinfo.jsp">Generate Invoice</a></li>
            <li><a href="./paycopay.jsp">Pay copay</a></li>
        </ul><br><br>
        <a href="./">Sign out</a>
        
    </body>
</html>
