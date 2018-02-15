
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Add new patient</h1>
        <form action="addpatient" method="post">
            Patient Name:               <input type="text" name="patient"><br>
            Doctor:
            <select name="doctor">
                <option value="doc1">Doctor 1</option>
                <option value="doc2">Doctor 2</option>
                <option value="doc3">Doctor 3</option>
                <option value="doc4">Doctor 4</option>
                <option value="doc5">Doctor 5</option>
            </select><br>
            Address:                    <input type="text" name="address"><br>
            Phone Number (only digits): <input type="text" name="phone"><br>
            Email:                      <input type="text" name="email"><br>
            Insurance:                  <input type="text" name="insurance"><br>
            SSN (no spaces):            <input type="text" name="SSN"><br>
            <input type="submit" value="Submit">
        </form>
        <a href="./staffhome.jsp">Back to home page</a>
    </body>
</html>
