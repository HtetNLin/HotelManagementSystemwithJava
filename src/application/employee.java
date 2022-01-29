package application;

public class employee {
	int d_id,e_id,Age;
	String Name,Nrc,Dob,Gender,Start_date,phone_no;
	String Salary;
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getNrc() {
		return Nrc;
	}
	public void setNrc(String nrc) {
		Nrc = nrc;
	}
	public String getDob() {
		return Dob;
	}
	public void setDob(String dob) {
		Dob = dob;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getStart_date() {
		return Start_date;
	}
	public void setStart_date(String start_date) {
		this.Start_date = start_date;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getSalary() {
		return Salary;
	}
	public void setSalary(String salary) {
		Salary = salary;
	}
	public employee( int e_id,int d_id, String name, int age, String nrc, String dob, String phone_no,
			String gender, String start_date, String salary) {
		super();
		this.d_id = d_id;
		this.e_id = e_id;
		this.Name = name;
		this.Age = age;
		this.Nrc = nrc;
		this.Dob = dob;
		this.Gender = gender;
		this.Start_date = start_date;
		this.phone_no = phone_no;
		this.Salary = salary;
	}
}
	