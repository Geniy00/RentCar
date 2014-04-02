package ua.nau.rentcar.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nau.rentcar.database.Connector;
import ua.nau.rentcar.model.beans.LocationBean;

public class LocationDAO {

	public static final String SQL_ADD_LOCATION = "insert into location (country, city, street) values (?, ?, ?)";
	public static final String SQL_GET_LOCATION_BY_DATA = "select * from location where country=? and city =? and street=?";
	public static final String SQL_GET_LOCATION_BY_ID = "select * from location where idlocation=?";
	
	
	private Connection connection = Connector.getConnection();
	
	/**
	 * Add new location, if it's possible
	 * @param country country value
	 * @param city city value
	 * @param street street value
	 * @return true - location was added, false - location wasn't added, maybe, it had already existed
	 */
	public boolean addLocation(String country, String city, String street){
		PreparedStatement statement;		
		
		try{
			statement = connection.prepareStatement(SQL_ADD_LOCATION);
			statement.setString(1, country);
			statement.setString(2, city);
			statement.setString(3, street);
			statement.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Add new location, if it's possible
	 * @param location LocationBean object
	 * @return true - location was added, false - location wasn't added, maybe, it had already existed
	 */
	public boolean addLocation(LocationBean location){
		return addLocation(location.getCountry(), location.getCity(), location.getStreet());
	}
		
	/**
	 * The method gets location by data
	 * @param country country value
	 * @param city city value
	 * @param street street value
	 * @return LocationBean object
	 */
	public LocationBean getLocation(String country, String city, String street){
		PreparedStatement statement;		
		ResultSet resultSet;
		
		try{
			statement = connection.prepareStatement(SQL_GET_LOCATION_BY_DATA);	
			statement.setString(1, country);
			statement.setString(2, city);
			statement.setString(3, street);
			resultSet = statement.executeQuery();
			
		} catch (SQLException e) {
			return null;
		}

		LocationBean location = null;
		try{
			while(resultSet.next()){
				location = new LocationBean();
				
				location.setId(Integer.parseInt(resultSet.getString("idlocation")));
				location.setCountry(resultSet.getString("country"));
				location.setCity(resultSet.getString("city"));
				location.setStreet(resultSet.getString("street"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;
	}
	
	/**
	 * The method gets location by data
	 * @param location location object, that contains country, city, street values
	 * @return LocationBean object
	 */
	public LocationBean getLocation(LocationBean location) {
		return getLocation(location.getCountry(), location.getCity(), location.getStreet());
	}
	
	/**
	 * The method get location by id
	 * @param id id value
	 * @return LocationBean objects
	 */
	public LocationBean getLocation(int id){
		PreparedStatement statement;
		ResultSet resultSet;
		
		try{
			statement = connection.prepareStatement(SQL_GET_LOCATION_BY_ID);			
			resultSet = statement.executeQuery();
			
		} catch (SQLException e) {
			return null;
		}

		LocationBean location = null;
		try{
			while(resultSet.next()){
				location = new LocationBean();
				
				location.setId(Integer.parseInt(resultSet.getString("idlocation")));
				location.setCountry(resultSet.getString("country"));
				location.setCity(resultSet.getString("city"));
				location.setStreet(resultSet.getString("street"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return location;			
	}
}
