
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;


public class ViewBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
		String searchField = request.getParameter("searchField");
		//System.out.println("Search          " + searchField);
		BookSetter objBook = new BookSetter(searchField);
		
		request.getRequestDispatcher("ViewBook.jsp").include(request, response);
		
		out.println("<div class='container'>");		
		List<BookSearch> list = DbUpdate.searchBook(objBook);
		
		out.println("<table class='table table-bordered'>");
		out.println("<br><br><tr><th>Isbn</th><th>Book Title</th><th>Author Name</th><th>Check Out</th></tr>");
		
		BorrowerSetter borrowerSetter = new BorrowerSetter();
		
		for(BookSearch bookSearch:list){
			//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td><td>"+bookSearch.getName()+"</td><td align=\"center\"><a href=\"#textboxid\" style=\"margin:auto; text-align:center; display:block;\" onClick=\"myPopUp()\">Click</a></td></tr>");
			
			if (bookSearch.getStatus().equals("Available")) {
				out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\"><a href='CheckOutBook?Isbn="+bookSearch.getIsbn()+"&Author="+bookSearch.getName()+"'>Available</a></td></tr>");
			}
			else {
				out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\">Not Available</td></tr>");
			}
			//out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\"><a href='CheckOutBook?Isbn="+bookSearch.getIsbn()+"&Author="+bookSearch.getName()+"&listSearch="+list+"'>Click</a></td></tr>");

			//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td></tr>");
		}
		out.println("</table>");
		
		out.println("</div>");
	out.close();
	}
}
