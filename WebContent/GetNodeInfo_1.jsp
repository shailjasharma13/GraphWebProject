<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="java.util.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="styles.css">
 <style type="text/css">
        .textStyle {
            color:white ; 
            margin-left: 30px ; 
            margin-top: 30px;
            font-size: x-large;
            
            
            
        }

        h1 {
            text-align: center;
            color: white;
            font-size: xx-large;
            font-family: monospace, 'Courier New';
            font-style: italic;
            font-weight: bold;
            text-decoration: underline;
            text-shadow: 1px 1px darkmagenta;
        }

        .textStyle2 {
            color: black;
            font-size: large;
        }

        .body {
            background: indigo;
            
        }
    </style>

<title>GetNodeInfo</title>
</head>
<body class="body">
<script>
function goBack() {
    window.history.back();
}
</script>
<div class="textStyle">
<form action="GetNodeInfo" method="get">
Select GraphName : <select name="TypeofGraph">
<c:forEach var="graphname" items="${graphlist}">
        <option><c:out value="${graphname}"/></option>
    </c:forEach>

</select>
<br><br>
Choose the type of Node : <select name="TypeofNode">
<option value="Entry Node">Entry Node</option>
<option value="Exit Node">Exit Node</option>
<option value="Fork Node">Fork Node</option>
<option value="Join Node">Join Node</option>
<option value="Linear Node">Linear Node</option>
</select>
<input type="submit" value="Get Nodes"/>
<%! @SuppressWarnings("unchecked") %>
<% String success=(String)request.getAttribute("success");
Map<Integer,List<Object>> addNodes = new HashMap<>();
addNodes=(Map<Integer,List<Object>>)request.getAttribute("addNodes");
if(success!=null)
	for (Integer i : addNodes.keySet()){
        List<Object> li =addNodes.get(i);
		   //response.getWriter().append("Node Id :: " + li.get(0) + " Node "
		   	//	+ "Name " + li.get(1) + "  node Weights : " + li.get(2));
        out.println("<body style='background-color:indigo; color:white;' >");
        out.println("  <br/>   ");
		   out.println("Node Id :: " + li.get(0) + " Node "
		   		+ "Name " + li.get(1) + "  node Weights : " + li.get(2));
		   out.println("  <br/>   ");

}
%>

</form><br><br>



<button onclick="goBack()">Go Back</button>


</div>
</body>
</html>