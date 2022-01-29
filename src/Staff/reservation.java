package Staff;

public class reservation {

	private int res_id,e_id,c_id,r_id;
	private String Check_in_date, Check_out_date, Booking_status,Reservation_cost;

	public reservation(int id,int eid,int cid,int rid,String cin, String cout, String bstatus, String resc) {
		super();
		this.res_id = id;
		this.e_id = eid;
		this.c_id = cid;
		this.r_id = rid;
		this.Check_in_date = cin;
		this.Check_out_date = cout;
		this.Booking_status = bstatus;
		this.Reservation_cost = resc;
		
	}

	public int getRes_id() {
		return res_id;
	}

	public void setRes_id(int res_id) {
		this.res_id = res_id;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getCheck_in_date() {
		return Check_in_date;
	}

	public void setCheck_in_date(String check_in_date) {
		Check_in_date = check_in_date;
	}

	public String getCheck_out_date() {
		return Check_out_date;
	}

	public void setCheck_out_date(String check_out_date) {
		Check_out_date = check_out_date;
	}

	public String getBooking_status() {
		return Booking_status;
	}

	public void setBooking_status(String booking_status) {
		Booking_status = booking_status;
	}

	public String getReservation_cost() {
		return Reservation_cost;
	}

	public void setReservation_cost(String reservation_cost) {
		Reservation_cost = reservation_cost;
	}

	}

