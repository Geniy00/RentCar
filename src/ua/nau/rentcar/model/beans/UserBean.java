package ua.nau.rentcar.model.beans;

import ua.nau.rentcar.util.UserTypeEnum;

/**
 * It's created on DB fields
 * It is used to transfer data to attributes
 * @author Geniy
 *
 */
public class UserBean {
	/**
	 * It's user's id in database
	 */
	private Integer id;
	/**
	 * It's user's login in DB
	 */
	private String login;
	/**
	 * It's a hash of user's password in DB
	 */
	private String password;
	/**
	 * It's user's first name in DB
	 */
	private String firstName;
	/**
	 * It's user's last name in DB
	 */
	private String lastName;
	/**
	 * It's user's permission.
	 * Permission has a list of accessible commands
	 */
	private UserTypeEnum permission;
	
	public UserBean(){		
		id = 0;		
		login = new String();
		firstName = new String();
		lastName = new String();
		permission = UserTypeEnum.ANONYMOUS;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserTypeEnum getPermission() {
		return permission;
	}
	public void setPermission(UserTypeEnum permission) {
		this.permission = permission;
	}
	
}
