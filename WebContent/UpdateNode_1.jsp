<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body class="body">
<div class="textStyle">
<form action="UpdateNode" method="get">
Select GraphName : 
<select name="TypeofGraph" >
<c:forEach var="graphname" items="${graphlist}">
        <option><c:out value="${graphname}"/></option>
    </c:forEach>
</select>
<input type="submit" value="Get Nodes"/>
<br><br>
</form>
<form action="UpdateNode">
Enter the source Node:
<select name="NodeName" onchange="this.form.submit">
<c:forEach var="node" items="${nodelist}">
        <option><c:out value="${node}"/></option>
    </c:forEach>
</select>
<br>
Update Name:
<input type="text" name="Name"/><br>
Update Weight:
<input type="text" name="weight"/>
<input type="submit" value="Update Node">
<% String success=(String)request.getAttribute("success");
if(success!=null)
out.println(success);
%>
</form>
</div>
</body>
</html>