package graph.project.part1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ConnectionDB {

	private static Connection connect = null;
	private static Statement statement = null;

	public void DBConnection() {

		try {
			System.out.println("I am inside DB connection");
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			System.out.println("load the driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/sampledb?" + "user=root&password=pass1234");

			// Statements allow to issue SQL queries to the database
			System.out.println("creating statement");
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			DatabaseMetaData meta = connect.getMetaData();

			ResultSet resultnodetype = meta.getTables(null, null, "nodetype", null);
			if (resultnodetype.next()) {
				statement.executeUpdate("DROP TABLE nodetype");
				System.out.println("dropped table Edge");
			}
			ResultSet resultedges = meta.getTables(null, null, "Edge", null);
			if (resultedges.next()) {
				statement.executeUpdate("DROP TABLE Edge");
				System.out.println("dropped table Edge");
			}
			ResultSet resultnodes = meta.getTables(null, null, "Node", null);
			if (resultnodes.next()) {
				statement.executeUpdate("DROP TABLE NODE");
				System.out.println("dropped table node");
			}
			ResultSet res = meta.getTables(null, null, "Graph", null);
			if (res.next()) {
				statement.executeUpdate("DROP TABLE GRAPH");
				System.out.println("dropped table graph");
			}

			ResultSet resview = meta.getTables(null, null, "GRAPH_INFO", null);
			if (resview.next()) {
				statement.executeUpdate("DROP VIEW GRAPH_INFO");
				System.out.println("dropped view graph info");
			}
			ResultSet resviewpath = meta.getTables(null, null, "PATH_INFO", null);
			if (resviewpath.next()) {
				statement.executeUpdate("DROP VIEW PATH_INFO");
				System.out.println("dropped view PATH_INFO");
			}
			ResultSet resTrigger = meta.getTables(null, null, "UPDATE_GRAPH", null);
			if (resTrigger.next()) 
			{
				statement.executeUpdate("DROP TRIGGER UPDATE_GRAPH");
				System.out.println("dropped TRIGGER UPDATE_GRAPH");
			}
			
			ResultSet reslongestPath = meta.getTables(null, null, "longestpath", null);
			if (reslongestPath.next()) 
			{
				statement.executeUpdate("DROP procedure longestpath");
				System.out.println("DROPPED procedure longestpath");
			}
			
			ResultSet resTablePath = meta.getTables(null, null, "path", null);
			if (resTablePath.next()) 
			{
				statement.executeUpdate("DROP table path");
				System.out.println("DROPPED table path");
			}

			// sql create table queries
			String createGraph = "CREATE TABLE Graph (" + "Graph_id  INTEGER AUTO_INCREMENT UNIQUE,"
					+ "Graph_Name  VARCHAR(20) UNIQUE," + "Set_of_nodes INTEGER," + "Set_of_directed_edges INTEGER,"
					+ "PRIMARY KEY (Graph_id)" + ");";

			String createNode = "CREATE TABLE node (" + "Node_id  INTEGER AUTO_INCREMENT UNIQUE,"
					+ "Node_name VARCHAR(50) UNIQUE," + "Graph_id  INTEGER," + "Number_Incoming_Edges INTEGER,"
					+ "Node_weight INTEGER," + "Number_Outgoing_Edges INTEGER," + "PRIMARY KEY (Node_id),"
					+ "FOREIGN KEY (Graph_id) REFERENCES Graph(Graph_id));";

			String createEdge = "CREATE TABLE Edge (" + "Edge_id  INTEGER AUTO_INCREMENT UNIQUE," + "Graph_id  INTEGER,"
					+ "Source_Node INTEGER," + "Target_Node INTEGER," + "Edge_Weight INTEGER,"
					+ "PRIMARY KEY (Edge_id)," + "FOREIGN KEY (Source_Node) REFERENCES Node(Node_id),"
					+ "FOREIGN KEY (Target_Node) REFERENCES Node(Node_id),"
					+ "FOREIGN KEY (Graph_id) REFERENCES Graph(Graph_id));";

			String createNodetype = "CREATE TABLE Nodetype (" + "Node_id  INTEGER," + "Node_Type varchar(50),"
					+ "Graph_id  INTEGER," + "PRIMARY KEY (Node_id,Node_Type),"
					+ "FOREIGN KEY (Node_id) REFERENCES Node(Node_id),"
					+ "FOREIGN KEY (Graph_id) REFERENCES Graph(Graph_id));";
			
			String tablepath="create table path(counter int , path varchar(50));";

			String createView = "CREATE VIEW graph_info AS " + "(" +
					"select distinct g1.graph_name as graph_name,g1.number_of_nodes as number_of_nodes, g2.number_of_edges as number_of_edges,g2.Node_Weight as Node_Weight , "+
					"g2.sum_of_edge_weights as sum_of_edge_weights " +
					"from "+
					"(SELECT graph_name, g.graph_id,count(n.node_id) AS number_of_nodes "+
										 "FROM graph g, node n  "+
					                     "WHERE g.graph_id=n.graph_id "+   
					                     "AND g.graph_id=n.graph_id "+
					                     "group by g.graph_id )G1 join "+
					"(SELECT n.graph_id,count(e.source_node) AS number_of_edges, sum(node_weight) AS Node_Weight, "+ 
							"sum(edge_weight) AS  sum_of_edge_weights "+
										 "FROM node n,edge e where "+
										 "e.graph_id=n.graph_id "+
										  "and n.node_id=e.source_node GROUP BY e.graph_id) G2 "+
					                     "on g1.graph_id=g2.graph_id "+
					                    ");";
			
			String update_graph_trigger="create trigger update_graph after insert on node "+ 
                                        "for each row update graph "+ 
                                        "set Set_of_nodes=Set_of_nodes+ "+ 
                                        "if((select count(*) from node "+ 
                                        "where graph_id=new.graph_id "+ 
                                        "group by graph_id)<=Set_of_nodes,1,0), "+
                                        "Set_of_directed_edges=Set_of_directed_edges+new.Number_Incoming_Edges+new.Number_Outgoing_Edges "+ 
                                        "where graph_id=new.graph_id;";
			String newLine = System.getProperty("line.separator");
			String node_info_trigger=
					"CREATE "+newLine+
					"TRIGGER node_info AFTER INSERT "+ 
					"ON  edge "+newLine+
					"FOR EACH ROW "+newLine+
					  "BEGIN "+newLine+
					        
				                "DECLARE outgoing_edge_node1 integer; "+newLine+ 
				                "DECLARE incoming_edge_node1 integer; "+newLine+
				                "DECLARE outgoing_edge_node2 integer; "+newLine+
				                "DECLARE incoming_edge_node2 integer; "+newLine+
				                
				                "SET outgoing_edge_node1=(SELECT COUNT(*) from edge where "+newLine+
				                "source_node=new.source_node and graph_id=new.graph_id group by source_node); "+newLine+

				                "SET incoming_edge_node1=(select count(*) from edge where "+newLine+
				                "target_node=new.source_node and graph_id=new.graph_id group by target_node); "+newLine+
				                      
				                "SET outgoing_edge_node2=(select count(*) from edge where "+newLine+
				                "source_node=new.target_node and graph_id=new.graph_id group by source_node); "+newLine+

				                "SET incoming_edge_node2 = (select count(*) from edge where "+newLine+
				                "target_node=new.target_node and graph_id=new.graph_id group by target_node); "+newLine+
				                
				                 
				                "delete from nodetype where node_id=new.source_node; "+newLine+
				                "delete from nodetype where node_id=new.target_node; "+newLine+
				   
				                "if (incoming_edge_node1 is null and outgoing_edge_node1 > 0) then "+newLine+
							                 "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.source_node,'Entry Node',new.graph_id); "+newLine+
				                "end if; "+newLine+

							    "if (outgoing_edge_node1 is null and incoming_edge_node1 > 0) then "+newLine+ 
							                  "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.source_node,'Exit Node',new.graph_id); "+newLine+
				                "end if; "+newLine+

							    "if (outgoing_edge_node1 > 1) then "+newLine+
				                               "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.source_node,'Fork Node',new.graph_id); "+newLine+ 		                        
				                "end if; "+newLine+

							     "if (incoming_edge_node1>1) then "+newLine+
							                    "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.source_node,'Join Node',new.graph_id); "+newLine+ 
				                 "end if; "+newLine+
							     "if (outgoing_edge_node1=1 AND incoming_edge_node1=1) then "+newLine+
							                    "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.source_node,'Linear Node',new.graph_id); "+newLine+
				                 "end if; "+newLine+

				                 "if (incoming_edge_node2 is null and outgoing_edge_node2 > 0) then "+newLine+ 
							                "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.target_node,'Entry Node',new.graph_id); "+newLine+
				                 "end if; "+newLine+

							     "if (outgoing_edge_node2 is null and incoming_edge_node2 > 0) then "+newLine+ 
							                   "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.target_node,'Exit Node',new.graph_id); "+newLine+
				                 "end if; "+newLine+

							     "if (outgoing_edge_node2>1) then "+newLine+
				                           "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.target_node,'Fork Node',new.graph_id); 	"+newLine+	                        
							     "end if; "+newLine+

							     "if (incoming_edge_node2>1) then "+newLine+
							               "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.target_node,'Join Node',new.graph_id); "+newLine+
				                 "end if; "+newLine+
							     "if (outgoing_edge_node2=1 AND incoming_edge_node2=1) then "+newLine+
							               "INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values (new.target_node,'Linear Node',new.graph_id); "+newLine+
				                 "end if; "+newLine+
					
				      "END";
			
			String longestPathProcedure=
					"CREATE PROCEDURE longestpath (IN sourcenode int,IN graphid int,IN path varchar(100))"+newLine+
					 "BEGIN"+newLine+
					  "DECLARE done int default 0;"+newLine+
					  "DECLARE PATH_TARGET_NODE VARCHAR(50);"+newLine+
					  "DECLARE TARGETNODE VARCHAR(50);"+newLine+
					  "DECLARE PATH_MIDDLE_NODE VARCHAR(50);"+newLine+
					  "DECLARE cur1 CURSOR FOR SELECT distinct target_node FROM edge where graph_id=graphid and source_node=sourcenode;"+newLine+
					  "DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;"+newLine+
					 
					  "SET max_sp_recursion_depth=255;"+newLine+
					  "set PATH_TARGET_NODE=(select node_name from node where node_id=sourcenode);"+newLine+
					  "set PATH_MIDDLE_NODE=path;"+newLine+
					  "open cur1;"+newLine+
					  
					   "read_loop:LOOP"+newLine+
					             
					             "fetch cur1 into targetnode;"+newLine+
					             "IF done = 1 THEN"+newLine+
					              "set path=(select concat(path,',',PATH_TARGET_NODE));"+newLine+
					               
								  "insert into path(counter,path) values (1,path);"+newLine+
					              "set path = PATH_MIDDLE_NODE;"+newLine+
					              "LEAVE read_loop;"+newLine+
								 "End if;"+newLine+
					             
					              
					              "set path=(select concat(path,',',PATH_TARGET_NODE));"+newLine+
					              "CALL longestpath(targetnode,graphid,path);"+newLine+
					              
								  "insert into path(counter,path) values (1,path);"+newLine+
					              "set path = PATH_MIDDLE_NODE;"+newLine+
					              
					     
					   "end loop;"+newLine+
					 
					  "close cur1;"+newLine+
					 "END";      
			
			
			
			String pathinfoview="Create view path_info as ( "+
 "SELECT " +
    "path, "+    
    "ROUND ("+   
        "("+
            "char_LENGTH(path) "+
            "- char_LENGTH( REPLACE ( path, ',', '') ) "+ 
        ")"+ 
            " / char_LENGTH(',') " +       
    ") AS count " +   
"FROM path );";
			// creating tables
			statement.executeUpdate(createGraph);
			System.out.println("created table graph");

			// creating tables
			statement.executeUpdate(createNode);
			System.out.println("created table Node");

			// create Edge
			statement.executeUpdate(createEdge);
			System.out.println("created table Edge");

			// create nodetype

			statement.executeUpdate(createNodetype);
			System.out.println("created table NodeType");
			
			statement.executeUpdate(tablepath);
			System.out.println("created table tablepath");
			

			System.out.println(createView);
			statement.executeUpdate(createView);
			System.out.println("created view graph info");
			
			System.out.println(pathinfoview);
			statement.executeUpdate(pathinfoview);
			System.out.println("created view path info view");
			
			
			
			// Insert three rows into graph table
			String insertRowGraph = "INSERT INTO graph (Graph_id,Graph_Name,Set_of_nodes,Set_of_directed_edges)"
					+ "VALUES (100, 'Graph1', 10, 11),(101, 'Graph2', 10, 12),(102, 'Graph3', 12, 14);";

			statement.executeUpdate(insertRowGraph);
			System.out.println("Inserted Rows in Table Graph");

			// Insert 11 rows into node table
			String insertRowNode = "INSERT INTO node (node_id,Node_name,Graph_id,number_incoming_edges,Number_outgoing_edges,Node_weight)"
					+ "VALUES (1,'n1',100,0,2,8),(2,'n2',100,1,1,16),(3,'n3',100,1,1,24),"
					+ "(4,'n4',100,2,1,32),(5,'n5',100,1,3,40),(6,'n6',100,1,1,48),"
					+ "(7,'n7',100,1,1,56),(8,'n8',100,1,1,64),(9,'n9',100,2,0,72),"
					+ "(10,'n10',100,1,0,80),(11,'n11',101,0,1,6),(12,'n12',101,1,3,12),"
					+ "(13,'n13',101,1,2,18),(14,'n14',101,1,1,24),(15,'n15',101,1,1,30),"
					+ "(16,'n16',101,2,1,36),(17,'n17',101,1,1,42),(18,'n18',101,2,1,48),"
					+ "(19,'n19',101,2,1,54),(20,'n20',101,1,0,60),(21,'n21',102,0,2,7),"
					+ "(22,'n22',102,1,1,14),(23,'n23',102,2,2,21),(24,'n24',102,2,1,28),"
					+ "(25,'n25',102,0,1,35),(26,'n26',102,1,1,42),(27,'n27',102,1,3,49),"
					+ "(28,'n28',102,1,0,56),(29,'n29',102,2,1,63),(30,'n30',102,1,1,70),"
					+ "(31,'n31',102,2,1,77),(32,'n32',102,1,0,84);";
			statement.executeUpdate(insertRowNode);
			System.out.println("Inserted Rows in Table Node");

			String insertEdgeNode = "INSERT INTO Edge(edge_id,graph_id,source_node,target_node,edge_weight)"
					+ "VALUES (1,100,1,2,5),(2,100,2,4,10),(3,100,1,3,15),"
					+ "(4,100,3,4,20),(5,100,4,5,25),(6,100,5,8,30)," + "(7,100,5,7,35),(8,100,5,6,40),(9,100,6,9,45),"
					+ "(10,100,7,9,50),(11,100,8,9,55),(12,101,11,12,2),"
					+ "(13,101,12,17,4),(14,101,12,13,6),(15,101,12,14,8),"
					+ "(16,101,13,15,10),(17,101,13,16,12),(18,101,14,16,14),"
					+ "(19,101,15,19,16),(20,101,16,18,18),(21,101,17,18,20),"
					+ "(22,101,18,19,22),(23,101,19,20,24),(24,102,21,22,3),"
					+ "(25,102,22,23,6),(26,102,23,26,9),(27,102,23,27,12),"
					+ "(28,102,24,23,15),(29,102,25,24,18),(30,102,21,24,21),"
					+ "(31,102,27,28,24),(32,102,26,29,27),(33,102,27,29,30),"
					+ "(34,102,27,31,33),(35,102,29,30,36),(36,102,30,31,39)," + "(37,102,31,32,41);";

			statement.executeUpdate(insertEdgeNode);
			System.out.println("Inserted Rows in Table Edge");

			String insertNodeType = "INSERT INTO nodetype (node_id,Node_type,Graph_id)"
					+ "VALUES (1,'entry node',100),(1,'fork node',100),(2,'linear node',100),"
					+ "(3,'linear node',100),(4,'join node',100),"
					+ "(5,'fork node',100),(6,'fork node',100),(7,'linear node',100),"
					+ "(8,'linear node',100),(9,'exit node',100),(9,'join node',100),(10,'exit node',100),"
					+ "(11,'entry node',101),(12,'fork node',101),(13,'fork node',101),"
					+ "(14,'linear node',101),(15,'linear node',101),(16,'join node',101),"
					+ "(17,'linear node',101),(18,'join node',101),(19,'join node',101),"
					+ "(20,'exit node',101),(21,'entry node',102),(21,'fork node',102),(22,'linear node',102),"
					+ "(23,'fork node',102),(23,'join node',102),(24,'join node',102),"
					+ "(25,'entry node',102),(26,'linear node',102),(27,'fork node',102),"
					+ "(28,'exit node',102),(29,'join node',102),(30,'linear node',102),"
					+ "(31,'join node',102),(32,'exit node',102);";

			statement.executeUpdate(insertNodeType);
			System.out.println("Inserted Rows in Table Node Type");
			
			System.out.println(update_graph_trigger);
			statement.executeUpdate(update_graph_trigger);
			System.out.println("created trigger update_graph_trigger");
			
			System.out.println(node_info_trigger);
			//statement.execute("DELIMITER $$");
			statement.executeUpdate(node_info_trigger);
			//statement.execute("DELIMITER ;");
			System.out.println("created trigger node_info_trigger");
			statement.executeUpdate("DROP PROCEDURE IF EXISTS longestpath;");
			statement.executeUpdate(longestPathProcedure);
			System.out.println("created longestPathProcedure");

		}

		catch (ClassNotFoundException CE) {
			CE.printStackTrace();
		} catch (SQLException SE) {
			SE.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	public List<String> getGraph() {
		List<String> graphNameList = new ArrayList<String>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("load the driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/sampledb?" + "user=root&password=pass1234");
			statement = connect.createStatement();

			ResultSet resultSet = statement.executeQuery("select Graph_Name from graph");
			while (resultSet.next()) {

				String graph_name = resultSet.getString("Graph_Name");
				graphNameList.add(graph_name);
			}
			return graphNameList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graphNameList;
	}

	public void InsertNode(String nodeWeight,String graphName, String Nodename, String incomingEdges, String outgoingEdges) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("load the driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/sampledb?" + "user=root&password=pass1234");

			statement = connect.createStatement();

			String selectGraph = "select Graph_ID from graph where graph_name='" + graphName + "';";
			
            
			ResultSet resultSet = statement.executeQuery(selectGraph);
			int graph_id = 0;
			while (resultSet.next()) 
			{

				graph_id = resultSet.getInt("Graph_ID");

			}
			
			int outgoingEdges_int = Integer.parseInt(outgoingEdges.toString());
			int incoming_edges_int=Integer.parseInt(incomingEdges.toString());
			int node_Weight=Integer.parseInt(nodeWeight.toString());

			String insertRowNode = "INSERT INTO node (Node_name,number_incoming_edges,number_outgoing_edges,Graph_id,Node_weight)"
					+ "VALUES ('" + Nodename + "'," + incomingEdges + "," + outgoingEdges + "," + graph_id +"," +node_Weight  + ");";
			System.out.println(insertRowNode);
			statement.executeUpdate(insertRowNode);
			
			ArrayList<String> nodeType=new ArrayList<String>();
			if(incoming_edges_int==0)
				nodeType.add("Entry Node");
			if (outgoingEdges_int==0) 
				nodeType.add("Exit Node");
			if (outgoingEdges_int>1)
				nodeType.add("Fork Node");
			if (incoming_edges_int>1)
				nodeType.add("Join Node");
			if (outgoingEdges_int==1 && incoming_edges_int==1)
				nodeType.add("Linear Node");
			
			ResultSet rs_node=statement.executeQuery("select node_id from node where node_name='"+
			Nodename+"';");
			int node_id=0;
			while (rs_node.next()){
				node_id=rs_node.getInt("node_id");
			}
			for(String nodeinfo:nodeType)
			{
				 System.out.println("nodeinfo ::: "+nodeinfo);
			}
			for(String nodeinfo:nodeType)
			{
		    System.out.println("nodeinfo inside for::: "+nodeinfo);
			String node_Type="INSERT INTO nodetype (Node_Id ,Node_Type ,Graph_id) values ("+node_id+",'"+nodeinfo+"',"+graph_id+");";
			System.out.println("node_Type after ::: "+node_Type);
			//statement.executeUpdate(node_Type);
			}

			/*
			String set_of_nodes_query = "select set_of_nodes from graph where graph_name='" +graphName + "';";
			System.out.println(set_of_nodes_query);
			int set_of_nodes = 0;
			ResultSet resultSetnodes = statement.executeQuery(set_of_nodes_query);
			while (resultSetnodes.next()) 
			{

				set_of_nodes = resultSetnodes.getInt("set_of_nodes");
				System.out.println("Inside result set"+set_of_nodes);
			}
			System.out.println(set_of_nodes);

			String set_of_directed_edges_query = "select set_of_directed_edges from graph where graph_name='"
					+ graphName + "';";
			int set_of_edges = 0;
			ResultSet resultSetedges = statement.executeQuery(set_of_directed_edges_query);
			while (resultSetedges.next()) {

				set_of_edges = resultSetedges.getInt("set_of_directed_edges");
			}
			String updateGraph = "Update graph set set_of_nodes=" + set_of_nodes + "+1 and " + "set_of_directed_edges="
					+ set_of_edges + "+" + count + " where graph_name='" + graphName + "';";
			System.out.println(updateGraph);

			System.out.println("Inserted in node");
			statement.executeUpdate(updateGraph);

*/			/*
			 * String query =
			 * "update graph set Set_of_nodes=Set_of_nodes+1 and Set_of_directed_edges=Set_of_directed_edges+"
			 * +count + " where graph_name =?;"; System.out.println();
			 * PreparedStatement preparedStmt = connect.prepareStatement(query);
			 * preparedStmt.setString(1, graphName);
			 * 
			 * // execute the java preparedstatement
			 * preparedStmt.executeUpdate(query);
			 */
			connect.close();
			System.out.println("Updated graph");
		} 
			catch (Exception e) 
			{
			e.printStackTrace();
		}

	}

	public Map<Integer, List<Object>> NodeInfo(String GraphName, String NodeType) {

		Map<Integer, List<Object>> addNodes = new HashMap<>();
		try {
			System.out.println("I am inside DB connection");
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB

			System.out.println("load the driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/sampledb?" + "user=root&password=pass1234");

			// Statements allow to issue SQL queries to the database
			String sqlQuery = null;
			System.out.println("creating statement");

			
				sqlQuery = "Select Node_Id,Node_Name,node_weight from node n,edge e"
						+ " where e.graph_id=(select graph_id" + " from graph where graph_name='" + GraphName + "') "
						+ " and n.graph_id=e.graph_id " + "and e.source_node=n.node_id "
						+ "and n.node_id IN (select node_id from nodetype nt where nt.node_type='" + NodeType
						+ "' and nt.graph_id= e.graph_id);";
			 

			System.out.println(sqlQuery);
			statement = connect.createStatement();
			ResultSet rs = statement.executeQuery(sqlQuery);
			int i = 0;
			while (rs.next()) {
				int nodeId = rs.getInt("node_id");
				String node_name = rs.getString("node_name");
				int node_weight = rs.getInt("node_weight");

				List<Object> li = new ArrayList<>();
				li.add(nodeId);
				li.add(node_name);
				li.add(node_weight);
				addNodes.put(i, li);
				i = i + 1;
			}
			return addNodes;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return addNodes;

	}

	public List<String> getNodes(String graphName) {
		List<String> nodeList = new ArrayList<String>();
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("load the driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/sampledb?" + "user=root&password=pass1234");
			statement = connect.createStatement();

			ResultSet resultSet = statement.executeQuery("select Node_name from node where graph_id=(select graph_id"
					+ " from graph where graph_name='" + graphName + "');");
			while (resultSet.next()) {

				String node_name = resultSet.getString("Node_name");
				nodeList.add(node_name);
			}
			return nodeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nodeList;

	}

	public void InsertEdge(String sourceNode, String targetNode, int weight) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("load the driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/sampledb?" + "user=root&password=pass1234");

			statement = connect.createStatement();

			String selectGraph = "select graph_id from " + "node  where node_name='" + sourceNode + "';";
			String selectSourceNodeId = "select Node_Id from Node where Node_name='" + sourceNode + "';";
			String selectTargetNodeId = "select Node_Id from Node where Node_name='" + targetNode + "';";
			System.out.println(selectGraph);
			System.out.println(selectSourceNodeId);
			System.out.println(selectTargetNodeId);

			ResultSet resultSet = statement.executeQuery(selectGraph);
			int graph_id = 0;
			while (resultSet.next()) {

				graph_id = resultSet.getInt("Graph_ID");

			}

			ResultSet resultSet_SourceNode = statement.executeQuery(selectSourceNodeId);

			int source_Node_id = 0;
			while (resultSet_SourceNode.next()) {

				source_Node_id = resultSet_SourceNode.getInt("Node_Id");

			}
			ResultSet resultSet_TargetNode = statement.executeQuery(selectTargetNodeId);
			int target_node_Id = 0;
			while (resultSet_TargetNode.next()) {

				target_node_Id = resultSet_TargetNode.getInt("Node_Id");

			}

			String insertRowEdge = "INSERT INTO edge (source_node,target_node,edge_weight,Graph_id)" + "VALUES ('"
					+ source_Node_id + "'," + target_node_Id + "," + weight + "," + graph_id + ");";

			statement.executeUpdate(insertRowEdge);

			System.out.println("Inserted in edge");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
