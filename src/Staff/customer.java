package Staff;

public class customer {
	
	int c_id,Age;
	String Name,Gender,Nrc,Passport,Ph_no;
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getNrc() {
		return Nrc;
	}
	public void setNrc(String nrc) {
		Nrc = nrc;
	}
	public String getPassport() {
		return Passport;
	}
	public void setPassport(String passport) {
		Passport = passport;
	}
	public String getPh_no() {
		return Ph_no;
	}
	public void setPh_no(String ph_no) {
		Ph_no = ph_no;
	}
	public customer(int c_id, String name,int age, String gender, String nrc, String passport, String ph_no) {
		super();
		this.c_id = c_id;
		Age = age;
		Name = name;
		Gender = gender;
		Nrc = nrc;
		Passport = passport;
		Ph_no = ph_no;
	}
	
	
}
