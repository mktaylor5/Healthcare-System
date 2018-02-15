<%-- 
    Document   : paycopay
    Created on : May 2, 2017, 6:19:39 PM
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
        <h1>Patient Copay</h1>
        <form action = "verifycopay" method ="post">
            Patient Name:       <input type="text" name="patient"><br>
            SSN:                <input type="text" name="SSN"><br>
            Payment Type:
            <select name="type">
                <option value="Card">Credit Card</option>
                <option value="Card">Debit Card</option>
                <option value="Check">Check</option>
                <option value="Cash">Cash</option>
            </select>
            <br>
            Card Holder Name:   <input type="text" name="cardName"><br>
            Card Number:        <input type="text" name="cardNum"><br>
            Expiration Date: 
            <select name="month">
                <option value="1">01</option>
                <option value="2">02</option>
                <option value="3">03</option>
                <option value="4">04</option>
                <option value="5">05</option>
                <option value="6">06</option>
                <option value="7">07</option>
                <option value="8">08</option>
                <option value="9">09</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <select name="year">
                <option value="2017">17</option>
                <option value="2018">18</option>
                <option value="2019">19</option>
                <option value="2020">20</option>
            </select>
            <br>
            CVV:    <input type="text" name="CVV"><br>
            Check Number: <input type="text" name="checkNum"><br>
            Amount: <input type="text" name="amount"><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
