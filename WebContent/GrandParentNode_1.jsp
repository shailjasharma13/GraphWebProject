<%@page import="java.util.ArrayList"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.INSTANCEOF"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.util.List" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Grand Parent Nodes</title>
</head>
<body class="body">
<div class="textStyle">
<br>
<form action="GrandParentNodes" method="get" >
Select a Graph Name:
<select name="TypeofGraph">
<c:forEach var="graphname" items="${graphlist}">
        <option><c:out value="${graphname}"/></option>
    </c:forEach>

</select><br>
<input type="submit" value="Get Nodes"/> 
<br>Enter the Node:
<select name="Node">
<c:forEach var="node" items="${nodelist}">
        <option><c:out value="${node}"/></option>
    </c:forEach>

</select>
<br>
<input type="submit" value="Get GrandParent Node"/>
</form>
<%! @SuppressWarnings("unchecked") %>
<% 
String success=(String)request.getAttribute("success");

List<String> nodeName=new ArrayList<String>();
if(request.getAttribute("nodenamelist") instanceof List<?>)
{
	
nodeName=(List<String>)request.getAttribute("nodenamelist");
}
if(success!=null){
	for(String node:nodeName){
		out.println("node name ::" +node);
	}
}%>
</div>
</body>
</html>