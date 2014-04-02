package ua.nau.rentcar.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nau.rentcar.database.Connector;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.model.beans.LocationBean;

public class CarDAO {
	
	private static final String SQL_ADD_CAR = "insert into car (mark, model, location_fk, price, status) values (?, ?, ?, ?, ?)";
	private static final String SQL_GET_ALL_CARS = "select * from car, location  where location_fk = idlocation order by mark, model";
	private static final String SQL_CHANGE_CAR_BY_ID = "update car set mark=?, model=?, location_fk=?, price=?, status=? where idcar=?";
	
	/**
	 * New connection object
	 */
	private Connection connection = Connector.getConnection();
	
	public CarDAO() { }
	
	public boolean addCar(CarBean carBean){
		PreparedStatement statement;
		
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.addLocation(carBean.getCountry(), carBean.getCity(), carBean.getStreet());
		
		
		try{
			statement = connection.prepareStatement(SQL_ADD_CAR);
			statement.setString(1, carBean.getMark());
			statement.setString(2, carBean.getModel());
			LocationBean location = locationDAO.getLocation(carBean.getCountry(), carBean.getCity(), carBean.getStreet());
			statement.setString(3, location.getId().toString());
			statement.setString(4, carBean.getPrice().toString());
			String status = (carBean.getStatus().equalsIgnoreCase("AVAILABLE")) ? "AVAILABLE" : "UNAVAILABLE";
			statement.setString(5, status);
			statement.execute();
			
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	/**
	 * This method is used to get all car's records in DB
	 * @param unavailable true - return all cars, false - return only available(worked) cars
	 * @return array of cars
	 */
	public CarBean[] getAllCars(boolean unavailable){
		
		PreparedStatement statement;
		ResultSet resultSet;
		try{
			statement = connection.prepareStatement(SQL_GET_ALL_CARS);
			resultSet = statement.executeQuery();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		ArrayList<CarBean> list = new ArrayList<>();
		try{
			while(resultSet.next()){
				
				CarBean car =  getRequestBean(resultSet);						
				
				if(unavailable || car.getStatus().equals("AVAILABLE")){
					list.add(car);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		CarBean[] cars = new CarBean[list.size()];
		list.toArray(cars);
		return cars;
	}
	
	/**
	 * This method get RequestBean from ResultSet object
	 * @param resultSet object for parsing data
	 * @return CarBean object
	 * @throws NumberFormatException exception
	 * @throws SQLException exception
	 */
	private CarBean getRequestBean(ResultSet resultSet) throws NumberFormatException, SQLException{
		CarBean car = new CarBean();
		
		car.setId(Integer.parseInt(resultSet.getString("idcar")));
		car.setMark(resultSet.getString("mark"));
		car.setModel(resultSet.getString("model"));
		car.setCountry(resultSet.getString("country"));
		car.setCity(resultSet.getString("city"));
		car.setStreet(resultSet.getString("street"));
		car.setPrice(Integer.parseInt(resultSet.getString("price")));
		car.setStatus(resultSet.getString("status"));
		
		return car;
	}
	
	/**
	 * This method return a car by  id	
	 * @param id - the id of car
	 * @return CarBean object with specific id
	 */
	public CarBean getCarById(int id){
		CarBean[] cars = getAllCars(true);
		
		CarBean car = null;
		
		for(CarBean current : cars){
			if(current.getId() == id){
				car = current;
				break;
			}
		}
		
		return car;
	}
	
	/**
	 * Update data in DB by CarBean object
	 * @param car new car value, that will be inserted in DB
	 * @return true - inserting was completed successfully, false - the value wasn't inserted
	 */
	public boolean updateCar(CarBean car){
		PreparedStatement statement;
		
		/**
		 * Add new location, if it's possible
		 */
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.addLocation(car.getCountry(), car.getCity(), car.getStreet());
		
		/**
		 * Update car table
		 */
		try{
			
			statement = connection.prepareStatement(SQL_CHANGE_CAR_BY_ID);
			statement.setString(1, car.getMark());
			statement.setString(2, car.getModel());
			LocationBean location = locationDAO.getLocation(car.getCountry(), car.getCity(), car.getStreet());
			statement.setString(3, Integer.toString(location.getId()));
			statement.setString(4, Integer.toString(car.getPrice()));
			
			if(car.getStatus().equalsIgnoreCase("available")){
				statement.setString(5, "AVAILABLE");
			} else{
				statement.setString(5, "UNAVAILABLE");
			}
			statement.setString(6, Integer.toString(car.getId()));
			
			statement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
				
		return true;		
	}
}
