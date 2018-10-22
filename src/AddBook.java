

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddBook extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		String isbn = request.getParameter("isbn");
		String bookName = request.getParameter("book_name");
		String authorName = request.getParameter("author_name");

		BookSetter objBook = new BookSetter(isbn, bookName, authorName);

		int temp = DbUpdate.enterBook(objBook);

		if (temp  != 0) {
			out.println("<script>window.alert(\"Book added successfully\")</script>");
			request.getRequestDispatcher("home.jsp").include(request, response);
		}
		else {
			out.println("<script>window.alert(\"Duplicate Entries. Book written by mentioned author already exists in the Library\")</script>");
			request.getRequestDispatcher("AddBookForm.jsp").include(request, response);	
		}
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
