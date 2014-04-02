package ua.nau.rentcar.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import ua.nau.rentcar.database.Connector;
import ua.nau.rentcar.model.beans.RequestBean;

public class RequestDAO {
			
	/**
	 * SQL queries
	 */
	public static final String SQL_GET_ALL_REQUESTS = "select * from request order by idrequest desc";
	public static final String SQL_ADD_REQUEST = "insert into request (user_fk, car_fk, begin_date, end_date, passport, request_message, response_message, cost, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String SQL_FIND_REQUEST_BY_ID = "select * from request where idrequest=?";
	public static final String SQL_FIND_REQUEST_BY_DATA = "select * from request where user_fk=? and car_fk=? and begin_date=? and end_date=? and passport=?";
	public static final String SQL_ACCEPT_REQUEST = "update request set  response_message =?, status='ACCEPTED' where idrequest=?";
	public static final String SQL_REFUSE_REQUEST = "update request set  response_message =?, status='REFUSED' where idrequest=?";
	
	private Connection connection = Connector.getConnection();
	
	/**
	 * This method get all RequestBean records from DB
	 * @return RequestBean[] object
	 */
	public RequestBean[] getAllRequests(){
				
		PreparedStatement statement;
		ResultSet resultSet;
		try{
			statement = connection.prepareStatement(SQL_GET_ALL_REQUESTS);
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
		
		ArrayList<RequestBean> list = new ArrayList<>();
		try{
			while(resultSet.next()){
				RequestBean requestBean = readRequestBean(resultSet);				
				list.add(requestBean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		RequestBean[] requests = new RequestBean[list.size()];
		list.toArray(requests);
		return requests;
	}
	
	/**
	 * This method get all requests for some user
	 * @param userId the id of user
	 * @return RequestBean[] object
	 */
	public RequestBean[] getRequestByUserId(int userId){
		RequestBean[] requestBeans = getAllRequests();
		ArrayList<RequestBean> list = new ArrayList<>(Arrays.asList(requestBeans));
		
		for(Iterator<RequestBean>  iterator = list.iterator(); iterator.hasNext(); ){
			RequestBean current = iterator.next();
			if(current.getUserFK() != userId){
				iterator.remove();
			}
		}
		
		RequestBean[] result = new RequestBean[list.size()];
		list.toArray(result);
		return result;
	}
	
	/**
	 * The method add new record in request's table
	 * @param requestBean RequestBean object that will be inserted in DB
	 * @return true - record was inserted, false - record wasn't inserted
	 */
	public boolean addRequest(RequestBean requestBean){
		
		PreparedStatement statement;
		try{
			statement = connection.prepareStatement(SQL_ADD_REQUEST);
			statement.setString(1, requestBean.getUserFK().toString());
			statement.setString(2, requestBean.getCarFK().toString());				
			//java.util.Date beginDate = new SimpleDateFormat("dd/MM/yyyy").parse(requestBean.getBeginDate());
			statement.setString(3, requestBean.getBeginDate());
			statement.setString(4, requestBean.getEndDate());
			statement.setString(5, requestBean.getPassport());
			statement.setString(6, requestBean.getRequestMessage());
			statement.setString(7, requestBean.getResponseMessage());
			statement.setString(8, requestBean.getCost().toString());
			
			String status = "PENDING";
			if(status.equalsIgnoreCase("accepted")) status = "ACCEPTED";
			if(status.equalsIgnoreCase("refused")) status = "REFUSED";			
			statement.setString(9, status);
			
			statement.execute();
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/**
	 * The method gets RequestBean by id
	 * @param id the id of finding request
	 * @return RequestBean object
	 */
	public RequestBean getRequestById(Integer id){
		PreparedStatement statement;
		ResultSet resultSet;
		RequestBean result = null;
		try{
			statement = connection.prepareStatement(SQL_FIND_REQUEST_BY_ID);
			statement.setString(1, id.toString());	
			resultSet = statement.executeQuery();
			
			resultSet.next();			
			result = readRequestBean(resultSet);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	/**
	 * This method shearches request record by userFK, carFK, beginDate, endDate, passport values
	 * This group of values are unique in DB
	 * @param requestBean object that contains initialized fields
	 * @return RequestBean object
	 */
	public RequestBean getRequestByData(RequestBean requestBean){
		
		PreparedStatement statement;
		ResultSet resultSet;
		RequestBean result = null;
		try{
			statement = connection.prepareStatement(SQL_FIND_REQUEST_BY_DATA);
			statement.setString(1, requestBean.getUserFK().toString());
			statement.setString(2, requestBean.getCarFK().toString());
			statement.setString(3, requestBean.getBeginDate());
			statement.setString(4, requestBean.getEndDate());
			statement.setString(5, requestBean.getPassport());
			
			resultSet = statement.executeQuery();
			
			resultSet.next();			
			result = readRequestBean(resultSet);
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	
	/**
	 * This method makes request accepted
	 * @param id id of request that will be accepted
	 * @param responseMessage response message from admin to user
	 * @return true - act completed successfully, false - act was broken
	 */
	public boolean acceptRequest(Integer id, String responseMessage){
		PreparedStatement statement;
		try{
			statement = connection.prepareStatement(SQL_ACCEPT_REQUEST);
			statement.setString(1, responseMessage);
			statement.setString(2, id.toString());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	/**
	 * This method makes request refused
	 * @param id id of request that will be refused
	 * @param responseMessage response message from admin to user
	 * @return true - act completed successfully, false - act was broken
	 */
	public boolean refuseRequest(Integer id, String responseMessage){
		PreparedStatement statement;
		try{
			statement = connection.prepareStatement(SQL_REFUSE_REQUEST);
			statement.setString(1, responseMessage);
			statement.setString(2, id.toString());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	/**
	 * Read all collumns of request table to RequestBean object
	 * @param resultSet - result set
	 * @return RequestBean object
	 * @throws NumberFormatException exception
	 * @throws SQLException exception
	 */
	private RequestBean readRequestBean(ResultSet resultSet) throws NumberFormatException, SQLException{
		RequestBean request = new RequestBean();
		
		Integer id = Integer.parseInt(resultSet.getString("idrequest"));
		Integer userFK = Integer.parseInt(resultSet.getString("user_fk"));
		Integer carFK = Integer.parseInt(resultSet.getString("car_fk"));
		//Date beginDate = new Date(Long.parseLong(resultSet.getString("begin_date")));
		//Date endDate = new Date(Long.parseLong(resultSet.getString("end_date")));
		String beginDate = resultSet.getString("begin_date");
		String endDate = resultSet.getString("end_date");
		String passport = resultSet.getString("passport");
		String requestMessage = resultSet.getString("request_message");
		String responseMessage = resultSet.getString("response_message");
		Integer cost = Integer.parseInt(resultSet.getString("cost"));
		String status = resultSet.getString("status");
		
		request = new RequestBean(id, userFK, carFK, beginDate, endDate, passport, requestMessage, responseMessage, cost, status);
		return request;
	}
	
	
}
