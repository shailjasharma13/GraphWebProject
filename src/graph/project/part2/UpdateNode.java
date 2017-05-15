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
 * Servlet implementation class UpdateNode
 */
@WebServlet("/UpdateNode")
public class UpdateNode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nameOfNode =request.getParameter("NodeName");
		String graphName =request.getParameter("TypeofGraph");
		String nodeName=request.getParameter("Name");
		String weight=request.getParameter("weight");
		
		List<String> graphNameList=new ArrayList<String>();
		List<String> nodeNameList=new ArrayList<String>();

		ConnectionDB graphdata=new ConnectionDB();
		ConnectionDB2 nodedata=new ConnectionDB2(); 

		
		if(nameOfNode==null && graphName==null)
		{
		graphNameList = graphdata.getGraph();
		request.setAttribute("graphlist", graphNameList);
		RequestDispatcher view=request.getRequestDispatcher("UpdateNode.jsp");
	    view.forward(request,response);
	    System.out.println("");
	    }
		
		if(graphName!=null)
		{
		nodeNameList = graphdata.getNodes(graphName);
		request.setAttribute("nodelist",nodeNameList);
		request.setAttribute("TypeofGraph", graphName);
		RequestDispatcher view=request.getRequestDispatcher("UpdateNode.jsp");
	    view.forward(request,response);
	    }
		
		if(nameOfNode!=null){
			int newWeight=0;
			if(weight!=null){
				newWeight= Integer.parseInt(weight.toString());
			}
			nodedata.updateNode(nameOfNode,nodeName,newWeight);
			request.setAttribute("success", "Node name " + nameOfNode + "  is updated");
			RequestDispatcher view=request.getRequestDispatcher("UpdateNode.jsp");
		    view.forward(request,response);
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
