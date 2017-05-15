<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Graph</title>
</head>
<body class="body">
<div class="textStyle">
<form action="CreateGraph">
Graph Name
<input type="text" name="Graphname"/><br>
Number of  Nodes
<input type="text" name="SetofNodes"/><br>
Number of Edges
<input type="text" name="Number of Edges"/><br>
<input type="submit" name="CreateGraph" value="Create Graph"><br><br>
<% String success=(String)request.getAttribute("success");
if(success!=null)
out.println(success);
%>
<br><br>
<input type="button" id="idname" value = "Create Node " onclick="goToNode()"/><br>
</form><br><br>
</div>
<script type="text/javascript">
        function goToNode(){
        	window.location = 'CreateNode.jsp';
        }

        </script>
</body>

</html>