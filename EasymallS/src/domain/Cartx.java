package domain;

public class Cartx {
	private int id;
	private int uid;
	private int pid;
	private Prod prod;
	private int count;

	public Cartx() {
	}

	public Cartx(int id, int uid, Prod prod, int count) {
		super();
		this.id = id;
		this.uid = uid;
		this.prod = prod;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Prod getProd() {
		return prod;
	}

	public void setProd(Prod prod) {
		this.prod = prod;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
