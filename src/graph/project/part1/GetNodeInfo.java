package graph.project.part1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetNodeInfo
 */
@WebServlet("/GetNodeInfo")
public class GetNodeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNodeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String typeofNode =request.getParameter("TypeofNode");
		String graphName =request.getParameter("TypeofGraph");
		Map<Integer,List<Object>> addNodes = new HashMap<>();
		ConnectionDB cb=new ConnectionDB();
		System.out.println("TypeofNode  : " +typeofNode+"  TypeofGraph:  "+ graphName);
		if(graphName==null)
		{
		List<String> graphNameList=new ArrayList<String>();
		graphNameList = cb.getGraph();
		request.setAttribute("graphlist", graphNameList);
		RequestDispatcher view=request.getRequestDispatcher("GetNodeInfo.jsp");
	    view.forward(request,response);
		}
		
		if(graphName!=null && typeofNode!=null)
		{
		addNodes=cb.NodeInfo(graphName, typeofNode);
		request.setAttribute("addNodes", addNodes);
		request.setAttribute("success", "success");
		RequestDispatcher view=request.getRequestDispatcher("GetNodeInfo.jsp");
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
