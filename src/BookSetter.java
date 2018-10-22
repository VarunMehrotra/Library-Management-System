

public class BookSetter {
	private String bookName;
	private String authorName;
	private String searchField;
	private String isbn;
	
	public BookSetter(String searchField) {
		this.searchField = searchField;
	}
	public BookSetter(String isbn, String bookName, String authorName) {
		this.bookName = bookName;
		this.authorName = authorName;
		this.isbn = isbn;
	}
	
	public String getbookName() {
		return bookName;
	}
	public void setbookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getauthorName() {
		return authorName;
	}
	public void setauthorName(String authorName) {
		this.authorName = authorName;
	}
	
	public String getsearchField() {
		return searchField;
	}
	public void setsearchField(String searchField) {
		this.searchField = searchField;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
}
