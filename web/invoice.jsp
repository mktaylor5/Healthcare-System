
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Care System</title>
    </head>
    <body>
        <h1>Invoice Summary</h1>
        Invoice Number: <%= session.getAttribute("invoiceNum") %><br>
        Service Provided: <%= session.getAttribute("serviceName") %><br>
        Cost: <%= session.getAttribute("totalCost") %><br>
        
        
        <!-- Verify payment will check to see if exp date is in future and card number is 16 digits,
             then ask patient if they want receipt emailed or back to invoice.jsp to re-enter info-->
        <form action = "verifypayment" method ="post">
            Card Holder Name:   <input type="text" name="cardName"><br>
            Card Number:        <input type="text" name="cardNum"><br>
            Expiration Date: 
            <select name="month">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <select name="year">
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
            </select>
            <br>
            CVV: <input type="text" name="CVV"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
