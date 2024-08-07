package QLy;


public class SignUp {

	private String userName;
    private String password;
 
    public SignUp() {
    }
 
    public SignUp(String userName, String password) {
      
        this.userName = userName;
        this.password = password;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    public static boolean Check (SignUp admin) {
		if(admin != null)
			if(admin.getUserName().equals("admin") && admin.getPassword().equals("1234"))
				return true;
		return false;
	}
}


