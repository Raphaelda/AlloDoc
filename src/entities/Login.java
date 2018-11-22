package entities;

import java.io.Serializable;

public class Login  implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private long login;
	private String password;
	  // Set the level of user's authorization 
	public enum Authorization {level1, level2 ,level3};
	private Authorization _authorization;
	
	//CONSTRACTOR
	
    public Login(long login,String password, Authorization authorization) {
    	this.login=login;
    	this.password = password;
		this._authorization = authorization;
	}
    public Login()
    {
		login =0;
		password = "";
		_authorization = Authorization.level1;
	}
	
    
	//Methods
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this) return true;
		
		if (obj == null || obj.getClass() != this.getClass()) return false;
				
		Login log = (Login) obj;
		return (
				this.login==log.login &&
				this.password.equals(log.password)&&
				this._authorization.equals(log._authorization)
				);
	}
	
	
	//GET AND SET
	public long getLogin() {
		return login;
	}
	public void setLogin(long login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Authorization getAuthorization() {
		return _authorization;
	}
	public void setAuthorization(Authorization authorization) {
		this._authorization = authorization;
	}
}
