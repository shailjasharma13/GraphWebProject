<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page import="java.util.List" %>
    <%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">   
<meta name="viewport" content="width=device-width, initial-scale=1">    
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
   
<title>Database Management Project</title>
<!-- Bootstrap Core CSS -->
    
<link href="css/bootstrap.min.css" rel="stylesheet">

 
   
<!-- Custom CSS: You can use this stylesheet to override any Bootstrap styles and/or apply your own styles -->
   
<link href="css/custom.css" rel="stylesheet">

   

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
       
<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    
<![endif]-->

    
<!-- Custom Fonts from Google -->
   
<link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
    
</head>

<body>

              <!-- Logo and responsive toggle -->
            <div class="navbar-header">
              
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                	<span class="glyphicon glyphicon-fire"></span> 
                	DBMS                </a>
                	  
            </div>
            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="navbar">
                

<ul class="nav navbar-nav navbar-right">
                    <li class="active">
                       
 <a href="Menu.jsp">Menu</a>
                   
</li>
  <li>
                        <a href="IntializeDb.jsp">Initialize Database</a>
                    </li>
                    					  <li>
                        <a href="Contact.jsp">Contact Us</a>
                    </li>
                    		
   </ul>
                
          
  </div>
       <!-- Header -->
    <header>
        <div class="header-content">
            <div class="header-content-inner">
            <font size="16">Information of Graph</font><br><br>


<%! @SuppressWarnings("unchecked") %>
 <% 
 Map<String,List<Integer>> addNodes = new HashMap<>();
addNodes=(Map<String,List<Integer>>)request.getAttribute("addNodes");
out.println("<table border='1' align='center'");
out.println(
		
        "<tr>"+
        "<td>"+"GraphName"+"</td>"+
        "<td>"+"Number of nodes"+"</td>"+
        "<td>"+"Number of Egdes"+"</td>"+
        "<td>"+"Weight Of Nodes"+"</td>"+
        "<td>"+"Weight Of Edges"+"</td>"+
        "</tr>");
for (String i : addNodes.keySet())
       {
          List<Integer> li =addNodes.get(i);
		   //response.getWriter().append("Node Id :: " + li.get(0) + " Node "
		   	//	+ "Name " + li.get(1) + "  node Weights : " + li.get(2));
          out.println("  <br/>   ");
          
          out.println(
          "<tr>"+
          "<td>"+i+"</td>"+
          "<td>"+li.get(0)+"</td>"+
          "<td>"+li.get(1)+"</td>"+
          "<td>"+li.get(2)+"</td>"+
          "<td>"+li.get(3)+"</td>"+
          "</tr>");
        }
 out.println("</table>");
  
 %>
 

 
<script type="text/javascript">
   function goBack() 
   {
   window.history.back();
   }
   </script>

<button onclick="goBack()" class="btn btn-primary btn-lg" >Go Back</button>


</div>
</div>

</header>
</body>
</html>