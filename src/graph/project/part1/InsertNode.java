package graph.project.part1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertNode
 */
@WebServlet("/InsertNode")
public class InsertNode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertNode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nameOfNode =request.getParameter("Nodename");
		String graphName =request.getParameter("TypeofGraph");
		String incomingEdges =request.getParameter("Incoming_edges");
		String outgoingEdges =request.getParameter("Outgoing_edges");
		String nodeWeight =request.getParameter("Node_weight");
		System.out.println("Nodename::;   " +nameOfNode);
		List<String> graphNameList=new ArrayList<String>();
		ConnectionDB graphdata=new ConnectionDB(); 
		
		if(nameOfNode==null)
		{
		graphNameList = graphdata.getGraph();
		request.setAttribute("graphlist", graphNameList);
		RequestDispatcher view=request.getRequestDispatcher("CreateNode.jsp");
	    view.forward(request,response);
	    }
	    
	
	if(nameOfNode!=null){
		graphdata.InsertNode(nodeWeight,graphName, nameOfNode,incomingEdges,outgoingEdges);
		String success="Node name " + nameOfNode + " is inserted";
		System.out.println(success);
		request.setAttribute("success", success);
		request.setAttribute("graphlist", graphNameList);
		request.setAttribute("Incoming_edges", incomingEdges);
		request.setAttribute("Outgoing_edges", outgoingEdges);
		request.setAttribute("Nodename", nameOfNode);
		RequestDispatcher view=request.getRequestDispatcher("CreateNode.jsp");
	    view.forward(request,response);}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
