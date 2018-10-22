
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.List;


public class UpdateFines extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		FineSetter objFine = new FineSetter();
		request.getRequestDispatcher("Styling.jsp").include(request, response);
		out.println("<h2>Aggregate Fine of all borrowers</h2>");
		
		out.println("<div class='container'>");		
		List<FineSetter> list = DbUpdate.getFines(objFine);
		
		if (list.isEmpty()) {
			out.println("<h3>No Pending Dues Exists !!</h3>");
		}
		else {
			out.println("<table class='table table-bordered'>");
			out.println("<br><tr><th>Card ID</th><th>First Name</th><th>Last Name</th><th>Total Fine</th><th>Show more</th></tr>");
			
			for(FineSetter objList:list){
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td><td>"+bookSearch.getName()+"</td><td align=\"center\"><a href=\"#textboxid\" style=\"margin:auto; text-align:center; display:block;\" onClick=\"myPopUp()\">Click</a></td></tr>");
				out.println("<tr><td align=\"center\">"+objList.getcardID()+"</td><td align=\"center\">"+objList.getBFname()+"</td><td align=\"center\">"+objList.getBLname()+"</td><td align=\"center\">"+objList.getfineAmount()+"</td><td align=\"center\"><a href='ShowPaymentDetails?CardID="+objList.getcardID()+"'>Click</a></td></tr>");
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td></tr>");
			}
			out.println("</table>");
		}	
		out.println("</div>");
	out.close();
	}
}
