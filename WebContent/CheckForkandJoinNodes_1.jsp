<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.INSTANCEOF"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css">
<title>Check Fork and Join Nodes</title>
</head>
<body class="body">
<div class="textStyle">
<br>
<form action="CheckForkandJoinNodes">
Select a Graph Name:
<select name="TypeofGraph">
<c:forEach var="graphname" items="${graphlist}">
        <option><c:out value="${graphname}"/></option>
    </c:forEach>

</select>
<input type="submit" value="Get fork and Join Nodes"/>
 <%! @SuppressWarnings("unchecked") %>
<% String success=(String)request.getAttribute("success");
List<String> nodeName=new ArrayList<String>();
if(request.getAttribute("nodelist") instanceof List<?>)
{
nodeName=(List<String>)request.getAttribute("nodelist");
}
if(success!=null){
	for(String node:nodeName){
		out.println("node name ::" +node);
	}
}%>
</form>
</div>
</body>
</html>