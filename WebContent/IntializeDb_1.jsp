<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <style type="text/css">
        .textStyle {
            color:#0000FF ; 
            margin-left: 330px ; 
            margin-top: 100px;
            font-size: x-large;
            text-shadow: 4px 4px lightblue;
            
            
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
<title>IntializeDb</title>
</head>
<body  class="body">
<h1 align="center"> Graph Database </h1>
<div class="textStyle">
<!--  <div style="color:#0000FF ; margin-left: 330px ; margin-top: 100px">-->
<form action="IntiazeDbServletGraph" method="get">
<input type="submit" value="Intialize Graph Database" align="right" height="40px" width="40px" ></input>
<button onclick="Menu.jsp">Menu</button>
</form>
</div>
</body>
</html>

