
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;


public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		//request.setCharacterEncoding("UTF-8");
		String searchField = request.getParameter("checkout");
		//System.out.println("Search          " + searchField);
		BookSetter objBook = new BookSetter(searchField);

		request.getRequestDispatcher("CheckoutBook.jsp").include(request, response);

		out.println("<div class='container'>");		
		List<BookSearch> list = DbUpdate.searchAvailableBook(objBook);

		
		BorrowerSetter borrowerSetter = new BorrowerSetter();
		
		if (list.isEmpty()) {
			out.println("<script>window.alert(\"Book not found !!\")</script>");
		}
		else {
			out.println("<table class='table table-bordered'>");
			out.println("<br><br><tr><th>Isbn</th><th>Book Title</th><th>Author Name</th><th>Check Out</th></tr>");
			
			for(BookSearch bookSearch:list){
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td><td>"+bookSearch.getName()+"</td><td align=\"center\"><a href=\"#textboxid\" style=\"margin:auto; text-align:center; display:block;\" onClick=\"myPopUp()\">Click</a></td></tr>");

//				if (bookSearch.getStatus().equals("Available")) {
//					out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\"><a href='CheckOutBook?Isbn="+bookSearch.getIsbn()+"&Author="+bookSearch.getName()+"'>Click</a></td></tr>");
//				}
				
				if (bookSearch.getStatus().equals("Available")) {
					out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\"><a href='CheckOutBook?Isbn="+bookSearch.getIsbn()+"&Author="+bookSearch.getName()+"'>Click</a></td></tr>");
				}
				else {
					out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\">Not Available</td></tr>");
				}
				
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td></tr>");
			}
			out.println("</table>");
		}	

		out.println("</div>");
		out.close();
	}
}
