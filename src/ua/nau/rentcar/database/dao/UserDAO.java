package ua.nau.rentcar.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nau.rentcar.database.Connector;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.util.UserTypeEnum;

/**
 * This class perform all actions related to user table in DB
 * @author Geniy
 *
 */
public class UserDAO {
	
	public static final String SQL_ADD_USER = "insert rentcar.user (login, password, permission, first_name, last_name) value (?, password(?), ?, ?, ?)";
	public static final String SQL_GET_USER_BY_LOGIN_PASSWORD = "select iduser, login, permission, first_name, last_name from user where login =? and password =password(?)";
	public static final String SQL_GET_USER_BY_ID = "select * from user where iduser=?";
	
	/**
	 * New connection object
	 */
	private Connection connection = Connector.getConnection();
	
	public UserDAO() { }
	
	public boolean addUser(String login, String password, String permission, String firstName, String lastName){
		PreparedStatement statement;
		try{
			statement = connection.prepareStatement(SQL_ADD_USER);
			statement.setString(1, login);
			statement.setString(2, password);
			statement.setString(3, permission);
			statement.setString(4, firstName);
			statement.setString(5, lastName);
			statement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	
	/**
	 * This method is used to authorization by login and password
	 * @param login the login of a user
	 * @param password the password of a user
	 * @return UserBean bean
	 */
	public UserBean getUserByLoginPassword(String login, String password){
		
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement = connection.prepareStatement(SQL_GET_USER_BY_LOGIN_PASSWORD);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}

		UserBean user = null;		
		
		try {
			if(!resultSet.next()) return null;
			
			user = new UserBean();
			
			int id = Integer.parseInt(resultSet.getString("iduser"));
			user.setId(id);
			user.setLogin(resultSet.getString("login"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));				
			String str = resultSet.getString("permission");
			user.setPermission(UserTypeEnum.valueOf(str));
							
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		return user;
	}
	
	/**
	 * The method gets user by id
	 * @param id id of user
	 * @return UserBEan object
	 */
	public UserBean getUserById(Integer id){
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement = connection.prepareStatement(SQL_GET_USER_BY_ID);
			statement.setString(1, id.toString());
			resultSet = statement.executeQuery();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}

		UserBean user = null;
		
		try{
			if(resultSet.next() == true) {
				user = readUserBean(resultSet);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		return user;
	}

	/**
	 * Read data from resultSet and put it in UserBean object
	 * @param resultSet
	 * @return UserBean object
	 */
	private UserBean readUserBean(ResultSet resultSet){
		UserBean user = new UserBean();
		
		try{
			user.setId(Integer.parseInt(resultSet.getString("iduser")));
			user.setLogin(resultSet.getString("login"));
			user.setPassword(resultSet.getString("password"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setLastName(resultSet.getString("last_name"));				
			String str = resultSet.getString("permission");
			user.setPermission(UserTypeEnum.valueOf(str));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		return user;
	}
}
