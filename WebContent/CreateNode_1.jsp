<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<link rel="stylesheet" href="styles.css">
<title>Create Node</title>
</head>
<body class="body">
<script type="text/javascript">
function checkForm(form)
{
	if(form.Nodename.value==""){
		alert("Error:NodeName is empty");
		return false;
		}
	if(form.Incoming_edges.value==""){
		alert("Error:Incoming_edges is empty");
		return false;
		}
	if(form.Outgoing_edges.value==""){
		alert("Error:Outgoing_edges is empty");
		return false;
		}
	if(isNaN(form.Incoming_edges.value)){
		alert("Error: Incoming_edges is not number");
		return false;
		}
	if(isNaN(form.Outgoing_edges.value)){
		alert("Error: Outgoing_edges is not a number");
		return false;
		}
	
	return true;
	}
	
function goBack() {
    window.history.back();
    
    function goToEdge(){
    	window.location = 'CreateEdge.jsp';
    }
}
</script>
<h1>Insert a node</h1><br>
<div class="textStyle">
<form action="InsertNode" method="get" onsubmit="return checkForm(this);" >
<br>
Select a Graph Name:
<select name="TypeofGraph">
<c:forEach var="graphname" items="${graphlist}">
        <option><c:out value="${graphname}"/></option>
    </c:forEach>

</select>
<br><br>
Name of input node:
<input type="text" name="Nodename" /><br><br>
Number of Incoming edges:
<input type="text" name="Incoming_edges" /><br><br>
Number of Outgoing edges:
<input type="text" name="Outgoing_edges" /><br><br>
<input type="submit" name="Create Node" value="Create Node"/><br>
<% String success=(String)request.getAttribute("success");
if(success!=null)
out.println(success);
%>
<input type="button" id="idname" value = "Create Edge" onclick="goToEdge()"/>
<br><br>

</form>





<button onclick="goBack()">Go Back</button>
</div>
</body>
</html>