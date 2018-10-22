

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

public class CheckOutBook extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String isbn = request.getParameter("Isbn");
		SubmitCheckOut.isbn = isbn;
		
		String author = request.getParameter("Author");

		
		BookLoanSetter bLSetter = new BookLoanSetter(isbn, author);
		int temp = 0;
		temp = DbUpdate.isBookAvailable(bLSetter);

		if ( temp != 0 ) {
			out.println("<script>window.alert(\"Checkout Failed. Book is already Checked out\")</script>");
			request.getRequestDispatcher("ViewBook.jsp").include(request, response);
			
//			out.println("<div class='container'>");	
//			out.println("<table class='table table-bordered'>");
//			out.println("<br><br><tr><th>Isbn</th><th>Book Title</th><th>Author Name</th><th>Check Out</th></tr>");
//			
//			List<BookSearch> list = (List<BookSearch>) request.getParameter("listSearch");
//			for(BookSearch bookSearch:list){
//				
//				out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\"><a href='CheckOutBook?Isbn="+bookSearch.getIsbn()+"&Author="+bookSearch.getName()+"'>Click</a></td></tr>");
//
//			}
		}
		else {
			List<BookSearch> list = DbUpdate.fetchBook(bLSetter);
			request.getRequestDispatcher("Styling.jsp").include(request, response);
			out.println("<div class='container'>");
			out.println("<h2>Check Out Confirmation</h2>");
			out.println("<form name=\"SubmitCheckOut\" action=\"SubmitCheckOut\" method=\"post\" onsubmit=\"return validateForm()\">");
			out.println("<table class='table table-bordered'>");
			out.println("<br><br><tr><th>Isbn</th><th>Book Title</th><th>Author Name</th><th>Borrower ID</th></tr>");

			for(BookSearch bookSearch:list){
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td><td>"+bookSearch.getName()+"</td><td align=\"center\"><a href=\"#textboxid\" style=\"margin:auto; text-align:center; display:block;\" onClick=\"myPopUp()\">Click</a></td></tr>");
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td><td>"+bookSearch.getName()+"</td><td align=\"center\"><input type=\"text\" id=\"bID\"></td><td align=\"center\"><a href='ConfirmCheckOut?Isbn="+bookSearch.getIsbn()+"?bID='>Click</a></td></tr>");
				out.println("<tr><td align=\"center\">"+bookSearch.getIsbn()+"</td><td align=\"center\">"+bookSearch.getTitle()+"</td><td align=\"center\">"+bookSearch.getName()+"</td><td align=\"center\"><input type=\"text\" name=\"bID\"></td></tr>");
				//out.println("<tr><td>"+bookSearch.getIsbn()+"</td><td>"+bookSearch.getTitle()+"</td></tr>");
			}
			out.println("</table>");
			out.println("</div>");

			out.println("<button type=\"submit\" class=\"btn btn-primary\">Submit</button>");
			out.println("</form>");

		}
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
