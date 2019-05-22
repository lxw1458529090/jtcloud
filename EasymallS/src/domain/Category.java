package domain;

public class Category {
	private int id;
	private String cname;
	public Category() {
	}
	public Category(int id, String cname) {
		super();
		this.id = id;
		this.cname = cname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
