package graph.project.part2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateGraph
 */
@WebServlet("/CreateGraph")
public class CreateGraph extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGraph() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String graphName =request.getParameter("Graphname");
		String setofNodes =request.getParameter("SetofNodes");
		String  noOfEdges =request.getParameter("Number of Edges");
	    int Set_of_nodes=Integer.parseInt(setofNodes.toString());
	    int Set_of_directed_edges =Integer.parseInt(noOfEdges.toString());
	    ConnectionDB2 con2=new ConnectionDB2();
	    con2.InsertGraph(graphName, Set_of_nodes, Set_of_directed_edges);
	    String success="Graph Name "+graphName+" is created";
		request.setAttribute("success", success);
		RequestDispatcher view=request.getRequestDispatcher("CreateGraph.jsp");
	    view.forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
