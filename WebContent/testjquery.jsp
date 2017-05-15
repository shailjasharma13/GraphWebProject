<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*"%>
    <%@ page import="com.google.gson.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	// (A) database connection
	// "jdbc:mysql://localhost:3306/northwind" - the database url of the form jdbc:subprotocol:subname
	// "dbusername" - the database user on whose behalf the connection is being made
	// "dbpassword" - the user's password
	Connection dbConnection = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/sampledb?"
		              + "user=root&password=pass1234");
	// (B) retrieve necessary records from database
	Statement getFromDb = dbConnection.createStatement();
	ResultSet employees = getFromDb
			.executeQuery("SELECT Graph_id,graph_name, Set_of_nodes,Set_of_directed_edges FROM graph");
	// (C) format returned ResultSet as a JSON array
	JsonArray recordsArray = new JsonArray();
	while (employees.next()) {
		JsonObject currentRecord = new JsonObject();
		currentRecord.add("Graph_id",
				new JsonPrimitive(employees.getString("Graph_id")));
		currentRecord.add("Graph_Name",
				new JsonPrimitive(employees.getString("Graph_Name")));
		currentRecord.add("Set_of_nodes",
				new JsonPrimitive(employees.getString("Set_of_nodes")));
		currentRecord.add("Set_of_directed_edges",
				new JsonPrimitive(employees.getString("Set_of_directed_edges")));
		recordsArray.add(currentRecord);
	}
	// (D)
	out.print(recordsArray);
	//out.flush();
%>

</body>
</html>