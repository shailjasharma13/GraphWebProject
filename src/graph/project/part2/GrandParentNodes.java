package graph.project.part2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import graph.project.part1.ConnectionDB;

/**
 * Servlet implementation class GrandParentNodes
 */
@WebServlet("/GrandParentNodes")
public class GrandParentNodes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrandParentNodes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String graphName =request.getParameter("TypeofGraph");
        List<String> graphNameList=new ArrayList<String>();;
        List<String> nodeNameList=new ArrayList<String>();
        String node =request.getParameter("Node");
        System.out.println("node::"+node);
        
		ConnectionDB graphdata=new ConnectionDB();
		ConnectionDB2 getForkJoinNodes=new ConnectionDB2(); 

		
		if(node==null)
		{
		
		graphNameList = graphdata.getGraph();
		request.setAttribute("graphlist", graphNameList);
		List<String> nodeList=new ArrayList<String>();
		nodeList=graphdata.getNodes(graphName);
		request.setAttribute("nodelist", nodeList);
		request.setAttribute("TypeofGraph", graphName);
		RequestDispatcher view=request.getRequestDispatcher("GrandParentNode.jsp");
		view.forward(request,response);
		System.out.println("dispatched by graph");
	    }
		
		if(node!=null)
		{
		System.out.println("not null");
		nodeNameList=getForkJoinNodes.getGrandParentNode(node);
		request.setAttribute("success", new String("success"));
		request.setAttribute("nodenamelist", nodeNameList);
		request.setAttribute("TypeofGraph", graphName);
		request.setAttribute("Node", node);
		RequestDispatcher view=request.getRequestDispatcher("GrandParentNode.jsp");
	    view.forward(request,response);
	    System.out.println("dispatched by node");
	    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
