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
 * Servlet implementation class InsertEdge
 */
@WebServlet("/InsertEdge")
public class InsertEdge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertEdge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sourceNode =request.getParameter("SourceNode");
		String targetNode =request.getParameter("TargetNode");
		String  graphName =request.getParameter("TypeofGraph");
		
		
		int weight=0;
		if(request.getParameter("weight")!=null)
			weight =Integer.parseInt(request.getParameter("weight").toString());		
		ConnectionDB db =new ConnectionDB();
		if(sourceNode == null && targetNode==null)
		{
		
		List<String> graphNameList=new ArrayList<String>();
		graphNameList = db.getGraph();
		request.setAttribute("graphlist", graphNameList);
		List<String> nodeList=new ArrayList<String>();
		nodeList=db.getNodes(graphName);
		request.setAttribute("nodelist", nodeList);
		request.setAttribute("TypeofGraph", graphName);
		RequestDispatcher view=request.getRequestDispatcher("CreateEdge.jsp");
	    view.forward(request,response);
}
		
		
		//request.setAttribute("TypeofGraph",graphName );
		if(sourceNode !=null && targetNode!=null && weight!=0)
		{
	    db.InsertEdge(sourceNode, targetNode,weight);	
	    String success="Edge between " + sourceNode +" and " + targetNode+ " is inserted";
		System.out.println(success);
		request.setAttribute("success", success);
		RequestDispatcher view=request.getRequestDispatcher("CreateEdge.jsp");
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
