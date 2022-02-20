
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<html>
<head>
    <title>Program</title>
    <script>
        function checkInput() {
            var checked = false;
            for(var i=0; i<reg.idItem.length; i++) {
                if(reg.idItem[i].checked) {
                    checked = true;
                    break;
                }
            }
            if(!checked) {
                document.getElementById("errMsg").innerHTML = "No checked items";
                return false;
            }
            reg.submit();
        }
    </script>
</head>
<body>
<h2>Welcome to our online grocery store!</h2>
<table border=0>
    <tr>
        <td>Date:</td>
        <td><%
   Date date = new Date();
   out.print( "<h3 align=\"left\">" +date.toString()+"</h3>");
%></td>
    </tr>
    <tr>
        <h2>ON SALE TODAY:</h2>
        
    </tr>
</table>
<p/>

<c:if test="${empty purchase}">
    <p style="color:red;">
        Grocery store is under construction...
    </p>

</c:if>
<c:if test="${not empty purchase}">
    <form name="reg" action="<c:url value='/check'/>" method=get onsubmit="return false">
        <table border=0>
            <tr>
            <th> </th>
            <th> Item</th>
            <th> Price</th>
            <th> Quantity</th>
            <th> Total price</th>
            
            </tr>
            
            <c:forEach var="item" items="${purchase}">
                <tr align="center">
                    <td valign="middle"><input type=checkbox name=idItem value="${item.product.itemId}" checked/></td>
                    <td valign="middle">${item.product.title}</td>
                    <td valign="middle">${item.product.price}</td>
                    <td valign="middle"><input type=text name="quantity" value="${item.quantity}"/></td>
                    <td valign="middle">${item.getCost()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <p/>
        DiscountCard: <input type=text name="card"/>
        <p id="errMsg" style="color:red;"></p>
        <input type=button value="Buy" onclick="return checkInput()"/>
    </form>
</c:if>

<p/>
</body>
</html>