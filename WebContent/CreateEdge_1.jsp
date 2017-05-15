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
<title>Create Edge</title>
</head>
<body class="body">
<script type="text/javascript">
function checkForm(form)
{
	if(form.SourceNode.value==""){
		alert("Error:Source Node is empty");
		return false;
		}
	if(form.TargetNode.value==""){
		alert("Error:Target Node is empty");
		return false;
		}
	if(form.weight.value==""){
		alert("Error: Weight is empty");
		return false;
		}
	if(isNaN(form.weight.value)){
		alert("Error: Weight is not a number");
		return false;
		}
	
	return true;
	}
	
function goBack() {
    window.history.back();
}
</script>
<h1> Insert an Edge </h1>
<div class="textStyle">

<form action="InsertEdge" method="get">
Select the graphName:<select name="TypeofGraph">
<c:forEach var="graphname" items="${graphlist}">
        <option><c:out value="${graphname}"/></option>
    </c:forEach>
</select> &nbsp; &nbsp;
<br><input type="submit" value="Get Nodes" name="Get Nodes">
</form>
</div>
<div class="textStyle">
<form action="InsertEdge" method="get" onsubmit="return checkForm(this);">
Enter the source Node:
<select name="SourceNode">
<c:forEach var="node" items="${nodelist}">
        <option><c:out value="${node}"/></option>
    </c:forEach>

</select>
<br><br>
Enter the target Node:
<select name="TargetNode">
<c:forEach var="node" items="${nodelist}">
        <option><c:out value="${node}"/></option>
    </c:forEach>

</select><br><br>Enter the weight:

<input type="text" name="weight"/><br><br>
<input type="submit" value="Create Edge"><br>
<% String success=(String)request.getAttribute("success");
if(success!=null)
out.println(success);
%>
</form><br>
<button onclick="goBack()">Go Back</button>
</div>
</body>
</html>