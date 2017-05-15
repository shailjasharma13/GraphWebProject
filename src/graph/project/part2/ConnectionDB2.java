package graph.project.part2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionDB2 
{
	 private static Connection connect = null;
	  private static Statement statement = null;
	  
	 public void ConnectionStatementDB2()
		{
			try 
			{
				System.out.println("I am inside DB connection");
			// This will load the MySQL driver, each DB has its own driver
		      Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB

			System.out.println("load the driver");
		      connect = DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/sampledb?"
		              + "user=root&password=pass1234");
		      

		      // Statements allow to issue SQL queries to the database
		      System.out.println("creating statement");
		      statement = connect.createStatement();
		     
		     }
			catch(ClassNotFoundException CE)
			{
				CE.printStackTrace();
		     }
			catch(SQLException SE)
			{
				SE.printStackTrace();
		     }
			catch(Exception E)
			{
				E.printStackTrace();
		     }
			 
		}
	 
	 public void InsertGraph(String graphName,int Set_of_nodes,int Set_of_directed_edges)
	 {
		 ConnectionStatementDB2();
		 String createGraph ="Insert into graph(Graph_Name,Set_of_nodes,Set_of_directed_edges) "
			+ "VALUES ('"+graphName+"',"+Set_of_nodes+","+Set_of_directed_edges+");";
		 System.out.println(createGraph);
	      try{
			statement.executeUpdate(createGraph);
			connect.close();
			}catch(Exception E){
				E.printStackTrace();
			}
	      }
	 
	 public void DeleteNode (String nodeName)
	 {
		 ConnectionStatementDB2();
		 String deleteNodeType ="Delete from nodetype where node_id=(select node_id from node "
			 		+ "where node_name='"+nodeName+"');";
		 String deleteEdge ="Delete from edge where source_node=(select node_id from node "
		 		+ "where node_name='"+nodeName+"');";
		 String deleteTargetEdge ="Delete from edge where target_node=(select node_id from node "
			 		+ "where node_name='"+nodeName+"');";

		 String deleteNode ="Delete from node where node_name='"+nodeName+"';";
		 System.out.println(deleteNodeType +"  " + deleteEdge +"  " +deleteNode);
	      try{
	    	statement.executeUpdate(deleteNodeType);
	    	System.out.println("Deleted nodetype");
	    	statement.executeUpdate(deleteEdge);
	    	statement.executeUpdate(deleteTargetEdge);
	    	System.out.println("Deleted Edges");
            statement.executeUpdate(deleteNode);
			connect.close();
			}catch(Exception E){
				E.printStackTrace();
			}
	      }
	 
	 public void updateNode(String nameOfNode,String newName, int weight){
		 ConnectionStatementDB2();
		 try
		 {
			 
		 if(weight!=0){
		 String updateupdate="update edge set edge_weight="+weight+" "
		 		+ "where source_node=(select node_id from node "
			 		+ "where node_name='"+nameOfNode+"');";
		 System.out.println(updateupdate);
		 statement.executeUpdate(updateupdate);
		 System.out.println("weight updated");
		 }
		 if(newName!=null){
			 String updateNode ="update node set node_name='"+newName+"' "
			 		+ "where node_name='"+nameOfNode+"';";
			 System.out.println(updateNode);
			 statement.executeUpdate(updateNode);
			 System.out.println("name updated");
		 }
		            connect.close();
				}catch(Exception E){
					E.printStackTrace();
				}
		 
		 
	 }
	 
	 public List<String> SelectGraph(){
		 ConnectionStatementDB2();
		 ArrayList<String> graphlist=new ArrayList<String>();
		 String selectgraph=
				 "select graph_name from graph g,node n "+ 
		          "where n.graph_id=g.graph_id "+
		          "and (select count(*) from node n where "+ 
		          "n.graph_id=g.graph_id "+
		          "group by g.graph_id)> 10 "+		   
		          "group by graph_name "+
		          "having sum(n.node_weight)>40;";
          System.out.println(selectgraph);		
		 try {
			ResultSet rs=statement.executeQuery(selectgraph);
			while (rs.next()){
				 graphlist.add(rs.getString("graph_name"));
				 
			 }	
			return graphlist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return graphlist;
	 }
	 
	 public List<String> SelectExitandEntryNode(){
		 ConnectionStatementDB2();
		 ArrayList<String> nodeList=new ArrayList<String>();
		 String selectNode=
				 "select graph_name from graph "+ 
				 "where graph_id in ("+
				                    "select graph_id from nodetype "+ 
				                    "where node_type ='exit node' "+
				                    "and GRAPH_id in (select GRAPH_ID from nodetype "+ 
				                    "where node_type='entry node' group by graph_id having count(*)=2)"+
				                    "group by graph_id "+ 
				                    "having count(*)=2);"; 

          System.out.println(selectNode);		
		 try {
			ResultSet rs=statement.executeQuery(selectNode);
			while (rs.next()){
				nodeList.add(rs.getString("graph_name"));
				 
			 }	
			return nodeList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return nodeList;
	 }
	 
	 public List<String> ForkJoinNodes(String graphName){
		 ConnectionStatementDB2();
		 ArrayList<String> nodeList=new ArrayList<String>();
		 String graphId="select graph_id from graph where graph_name ='"+graphName+"';";
		 int id=0;
		 try {
				ResultSet res=statement.executeQuery(graphId);
				while (res.next())
				{
					id=res.getInt("graph_id");
					 
				 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 String selectNode=
				 "select node_name "+
				 "from node "+ 
				 "where node_id in ("+
				                   "select node_id "+ 
				                   "from nodetype "+
				                   "where node_type='fork node' "+ 
				                   "and graph_id="+id+
				                   " and  node_id in( "+
				                                  "select node_id "+ 
				                                  "from nodetype "+ 
				                                  "where graph_id="+id+
				                                  " and node_type='join node')"+
				                  " );";


          System.out.println(selectNode);		
		 try {
			ResultSet rs=statement.executeQuery(selectNode);
			while (rs.next()){
				nodeList.add(rs.getString("node_name"));
				 
			 }	
			return nodeList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return nodeList;
 
	 }
	 
	 
	 public List<String> getGrandParentNode(String node){
		 ConnectionStatementDB2();
		 ArrayList<String> nodeList=new ArrayList<String>();
		 String graphId="select graph_id from node where node_name ='"+node+"';";
		 int id=0;
		 try {
				ResultSet res=statement.executeQuery(graphId);
				while (res.next())
				{
					id=res.getInt("graph_id");
					 
				 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 System.out.println("id"+id);
		 String selectNode=
				 "select node_name "+ 
				 "from node " +
				 "where graph_id="+id+
				 " and node_id in ("+ 
				                   "select source_node "+
				                   "from edge "+ 
				                   "where graph_id="+id+
				                   " and target_node in ("+
				                                           "select source_node "+ 
				                                           "from edge "+ 
				                                           "where graph_id="+id+
				                                           " and target_node="+ 
				                                                             "("+
				                                                              "select node_id " + 
				                                                              "from node "+ 
				                                                              "where node_name='"+node+"'"+
				                                                               ")"+
				                                         ")"+
				                   ");";
          System.out.println(selectNode);		
		 try {
			ResultSet rs=statement.executeQuery(selectNode);
			while (rs.next()){
				nodeList.add(rs.getString("node_name"));
				 
			 }	
			return nodeList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return nodeList;
 
	 }
	 
	 
	 public List<String> getPipeLineNodes(String graphName){
		 ConnectionStatementDB2();
		 ArrayList<String> nodeList=new ArrayList<String>();
		 String graphId="select graph_id from graph where graph_name ='"+graphName+"';";
		 int id=0;
		 try {
				ResultSet res=statement.executeQuery(graphId);
				while (res.next())
				{
					id=res.getInt("graph_id");
					 
				 }
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 String selectNode=
				 "select node_name "+
				 "from node "+ 
				 "where node_id in ("+
				                   "select node_id "+ 
				                   "from nodetype "+
				                   "where node_type='linear node' "+ 
				                   "and graph_id="+id+
				                   ");";


          System.out.println(selectNode);		
		 try {
			ResultSet rs=statement.executeQuery(selectNode);
			while (rs.next()){
				nodeList.add(rs.getString("node_name"));
				 
			 }	
			return nodeList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 return nodeList;
 
	 }
	 
	 public Map<String,List<Integer>> graphInfo ()
		{
		 ConnectionStatementDB2();

			Map<String,List<Integer>> addNodes = new HashMap<>();
			try
			{
			 
		      System.out.println("creating statement");
		      ResultSet rs=statement.executeQuery("select * from graph_info");
	          
	          while(rs.next()){
	        	
	            String graph_name=rs.getString("graph_name");
	            int numberOfNodes=rs.getInt("number_of_nodes");
	            int numberOfEdges=rs.getInt("number_of_edges");
	            int sumOfWeights=rs.getInt("sum_of_edge_weights");
	            int node_weight=rs.getInt("Node_Weight");
	            List<Integer> li=new ArrayList<>();
	            li.add(numberOfNodes);
	            li.add(numberOfEdges);
	            li.add(sumOfWeights);
	            li.add(node_weight);
	            addNodes.put(graph_name, li);
	            
	          }
		      return addNodes;
		   }catch(Exception e){
			   
	                e.printStackTrace();
		   }
			return addNodes;
			

	}
	 
	 
	 public List<String> longestPath(String graphName)
	 {
		 ConnectionStatementDB2();
		 ArrayList<Integer> nodeList=new ArrayList<Integer>();
		 String  graph_id="select graph_id from graph where graph_name ='"+graphName+"';";
		 String path=null;
		 int graphid=0;
		 List<String> obj=new ArrayList<String>();
		 try {
			   statement.executeUpdate("truncate table path;");
				ResultSet res=statement.executeQuery(graph_id);
				while (res.next())
				{
					graphid=res.getInt("graph_id");
					 
				 }
				
	
				String  entrynode="select node_id from nodetype where node_type='Entry node' and "
						+ "graph_id ="+graphid+";";
				ResultSet resnode=statement.executeQuery(entrynode);
				int nodeid=0;
				
				while (resnode.next())
				{
					nodeid=resnode.getInt("node_id");
					nodeList.add(nodeid);
				 }
				ResultSet resnodename;
				for(int entrynodeid:nodeList)
				{
				String  nodename="select node_name from node where node_id="+entrynodeid+ " and graph_id ="+graphid+";";
				resnodename=statement.executeQuery(nodename);
				String node_name=null;
				while (resnodename.next())
				{
					node_name=resnodename.getString("node_name");
					 
				 }
					statement.executeQuery("CALL longestpath("+entrynodeid+","+graphid+",'"+node_name+"');");
				}
				String  longestpath="select distinct count AS count,path AS PATH from path_info where count= (select max(count) from path_info);";
				ResultSet resnodepath=statement.executeQuery(longestpath);
				int count=0;
				
				int i=0;
				while (resnodepath.next())
				{
					
					count=resnodepath.getInt("count");
					if(i==0 ){
						obj.add(String.valueOf(count));
					}
					path=resnodepath.getString("path");
					if(path!=null){
					path=path.substring(path.indexOf(',')+1);
					}
					
					obj.add(path);
				 }
				
				
				
				
				 return obj;
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		
 return obj;
	 }
	 
}
	  
	  

