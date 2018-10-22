
public class BookLoanSetter {
	private String bookName;
	private String authorName;
	private String searchField;
	private String isbn;
	
	public BookLoanSetter(String isbn, String authorName) {
		this.isbn = isbn;
		this.authorName = authorName;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getAuthor() {
		return authorName;
	}
	public void setAuthor(String authorName) {
		this.authorName = authorName;
	}
}
