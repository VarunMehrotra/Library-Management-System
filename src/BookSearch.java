
public class BookSearch {
	private String Isbn;
	private String Title;
	private String Name;
	private String status;
	
	public BookSearch() {
		super();
	}
	public BookSearch(String Isbn, String Title, String Name) {
		super();
		this.Isbn = Isbn;
		this.Title = Title;
		this.Name = Name;
	}
	
	public String getIsbn() {
		return Isbn;
	}
	public void setIsbn(String Isbn) {
		this.Isbn = Isbn;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		this.Title = Title;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
}
