<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<!DOCTYPE html>
<html>
<head>

<title>Сheque</title>

</head>
<body>
<h2>CASH RECEIPT</h2>
<h3>Online grocery store</h3>
<table border=0>
    <tr>
        <td>Date:</td>
        <td><%
   Date date = new Date();
   out.print( "<h4 align=\"left\">" +date.toString()+"</h4>");
%></td>
    </tr>
 <table border=0>
            <tr>
            
            <th> Item</th>
            <th> Price</th>
            <th> Quantity</th>
            <th> Total price</th>
            
            </tr>
            
            <c:forEach var="item" items="${purchase}">
                <tr align="center">
                    
                    <td valign="middle">${item.product.title}</td>
                    <td valign="middle">${item.product.price}</td>
                    <td valign="middle">${item.quantity}</td>
                    <td valign="middle">${item.getCost()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
  <h3>------------------------------------</h3>
<table border=0>
        <tr>
        <td> TAXABLE TOT.:</td>
        <td></td>
         
        <td>${cost}</td>
        </tr>
        <tr>
        <td> Discount card 5%:</td>
        <td></td>
         
        <td>${discount}</td>
        </tr>
        <tr>
        <td>${card}</td>
        </tr>
        <tr>
        <td><h3> TOTAL</h3></td>
          <td></td>
         
        <td>${totalCost}</td>
        </tr>
 </table>
</body>
</html>