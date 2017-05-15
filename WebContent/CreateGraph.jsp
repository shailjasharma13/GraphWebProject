<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
            <font size="16">Create Graph</font><br><br>
<form action="CreateGraph">
<p>Graph Name :<font color="black">
<input type="text" name="Graphname"/><br><br>
</font>
Number of  Nodes :<font color="black">
<input type="text" name="SetofNodes"/><br><br>
</font>
Number of Edges :<font color="black">
<input type="text" name="Number of Edges"/><br>
</font>
</p>
<input type="submit"  class="btn btn-primary btn-lg" name="CreateGraph" value="Create Graph"><br><br>
<% String success=(String)request.getAttribute("success");
if(success!=null)
out.println(success);
%>
<br><br>
<p> Click here to create node :<input type="button" class="btn btn-primary btn-lg" id="idname" value = "Create Node " onclick="goToNode()"/><br>
</p>
</form>

<script type="text/javascript">
        function goToNode(){
        	window.location = 'InsertNode';
        }

        </script>
        
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
         <!-- jQuery -->
    <script src="js/jquery-1.11.3.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="js/jquery.easing.min.js"></script>
    
    <!-- Custom Javascript -->
    <script src="js/custom.js"></script>
        
</body>

</html>