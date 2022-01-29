package application;

public class Member {
	
		private String name;
		private String password;
		private String type;
		
		@Override
		public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s,$s",name,password);
		}

		public Member(String name, String password,String type) {
			super();
			this.name = name;
			this.password = password;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public String gettype() {
			return type;
		}
		
		public void settype(String type) {
			this.type = type;
		}
		
		
		
		
}
