package Staff;

public class rooms {
	int id;
	String type, price, status;

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getPrice() {
		return price;
	}

	public String getStatus() {
		return status;
	}

	public rooms(int id, String type, String price, String status) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.status = status;
	}
}
