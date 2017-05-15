<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu</title>
</head>
<body class="body">
<div class="textStyle">
<form action="GetNodeInfo">
<input type="submit" value="GetNodeInfo" name="">
</form>
<form action="InsertNode">
<input type="submit" value="Insert node" name="">
</form><br><br>
<form action="InsertEdge">
<input type="submit" value="Insert Edge" name="">
</form><br>
<form action="DeleteNode">
<input type="submit" value="DeleteNode" name="deleteNode">
</form>
<form action="UpdateNode">
<input type="submit" value="UpdateNode" name="updateNode">
</form>
<form action="SelectGraph">
<input type="submit" value="Graph having nodes>10" name="SelectGraph">
</form>
<form action="ExitandEntryNode">
<input type="submit" value="Graph having 2 Exit and Entry Node" name="ExirandEntryNode">
</form>
<form action="CheckForkandJoinNodes">
<input type="submit" value="Get Fork and Join Nodes" name="CheckForkandJoinNodes"/>
</form>
<form action="GrandParentNodes">
<input type="submit" value="Get GrandParent Nodes" name="GrandParentNodes"/>
</form>
<form action="PipelineNodes">
<input type="submit" value="Get Pipeline Nodes" name="GetPipeLineNodes"/>
</form>
<form action="GraphView">
<input type="submit" value="Graph Info" name="GetGraphInfo"/>
</form>
<form action="LongestPath">
<input type="submit" value="Get Longest Path" name="GetLongestPath"/>
</form>
</div>
<button ></button>
</body>
</html>