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
 * Servlet implementation class LongestPath
 */
@WebServlet("/LongestPath")
public class LongestPath extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LongestPath() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String graphName =request.getParameter("TypeofGraph");
        List<String> graphNameList=new ArrayList<String>();
        List<String> nodeNameList=new ArrayList<String>();

		ConnectionDB graphdata=new ConnectionDB();
		ConnectionDB2 getForkJoinNodes=new ConnectionDB2(); 

		
		if(graphName==null)
		{
		graphNameList = graphdata.getGraph();
		request.setAttribute("graphlist", graphNameList);
		RequestDispatcher view=request.getRequestDispatcher("LongestPath.jsp");
	    view.forward(request,response);
	    System.out.println("dispatched");
	    }
		if(graphName!=null)
		{
		nodeNameList=getForkJoinNodes.longestPath(graphName);
		request.setAttribute("success", new String("success"));
		request.setAttribute("nodelist", nodeNameList);
		request.setAttribute("TypeofGraph", graphName);
		RequestDispatcher view=request.getRequestDispatcher("LongestPath.jsp");
	    view.forward(request,response);
	    System.out.println("dispatched");
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
