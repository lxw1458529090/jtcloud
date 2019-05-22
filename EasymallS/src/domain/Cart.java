package domain;

public class Cart {
	// ���ﳵID
	private int id;
	// �û�ID
	private int uid;
	// ��ƷID
	private int pid;
	// ��Ʒ��
	private String pname;
	// ��Ʒ�۸�
	private double price;
	// ��ƷͼƬurl
	private String imgurl;
	// ��Ʒ��
	private int count;


	// �ܼ�
	private double sum;

	public Cart() {
	}

	public Cart(int uid, int pid) {
		this.uid = uid;
		this.pid = pid;
	}

	public Cart(int id, int uid, int pid, String pname, double price,
			String imgurl, int count) {
		this.id = id;
		this.uid = uid;
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.imgurl = imgurl;
		this.count = count;
		this.sum = this.count * this.price;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		this.sum = this.count * this.price;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		this.sum = this.count * this.price;
	}

	public double getSum() {
		return sum;
	}

}
