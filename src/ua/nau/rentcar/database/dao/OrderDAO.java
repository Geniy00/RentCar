package ua.nau.rentcar.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import ua.nau.rentcar.database.Connector;
import ua.nau.rentcar.model.beans.OrderBean;
import ua.nau.rentcar.model.beans.RequestBean;


public class OrderDAO {
	
	public static final String SQL_ADD_ORDER = "insert into rentcar.order (request_fk, comment, status, cost, additional_cost, repair_cost) values (?, ?, ?, ?, ?, ?)";
	public static final String SQL_GET_ALL_ORDERS = "select * from rentcar.order order by idorder desc";
	public static final String SQL_CHANGE_ORDER_BY_ID = "update rentcar.order set comment=?,  status=?, cost=?, additional_cost=?, repair_cost=? where idorder=?";
	
	private Connection connection = Connector.getConnection();
	
	/**
	 * This method gets all records from order table in DB
	 * @return array of OrderBean objects
	 */
	public OrderBean[] getAllOrders(){
		
		PreparedStatement statement;
		ResultSet resultSet;
		try{
			statement = connection.prepareStatement(SQL_GET_ALL_ORDERS);
			resultSet = statement.executeQuery();
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
		
		ArrayList<OrderBean> list = new ArrayList<>();
		try{
			while(resultSet.next()){
				OrderBean orderBean = readOrderBean(resultSet);
				list.add(orderBean);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		OrderBean[] result = new OrderBean[list.size()];
		list.toArray(result);
		return result;		
	}
	
	/**
	 * This method gets OrderBean by its id
	 * @param id id of OrderBean in DB
	 * @return OrderBean object
	 */
	public OrderBean getOrderById(Integer id){
		OrderBean[] orderBeans = getAllOrders();
		ArrayList<OrderBean> list = new ArrayList<>(Arrays.asList(orderBeans));
		
		for(OrderBean current : list){
			if(current.getId() == id) return current;
		}
		
		return null;
	}
	
	/**
	 * This method gets all orders for a user that has the special id
	 * @param userId user's id
	 * @return array of OrderBean objects
	 */
	public OrderBean[] getOrderByUserId(Integer userId){
		OrderBean[] orderBeans = getAllOrders();
		ArrayList<OrderBean> list = new ArrayList<>(Arrays.asList(orderBeans));
		
		RequestDAO requestDAO = new RequestDAO();
		
		for(Iterator<OrderBean> iterator = list.iterator(); iterator.hasNext(); ){
			
			OrderBean current = iterator.next();
			RequestBean requestBean = requestDAO.getRequestById(current.getRequestFK());
			
			if(requestBean.getUserFK() != userId){
				iterator.remove();
			}
		}
		
		OrderBean[] result = new OrderBean[list.size()];
		list.toArray(result);
		return result;
	}
	
	/**
	 * Add new order to DB
	 * @param orderBean orderBean object that will add to DB
	 * @return true - act complete successfully, false - act was broken  
	 */
	public boolean addOrder(OrderBean orderBean){
		
		PreparedStatement statement;
		try{
			 statement = connection.prepareStatement(SQL_ADD_ORDER);
			 statement.setString(1, orderBean.getRequestFK().toString());
			 statement.setString(2, orderBean.getComment());
			 statement.setString(3, "OPENED");
			 statement.setString(4, orderBean.getCost().toString());
			 statement.setString(5, orderBean.getAdditionalCost().toString());
			 statement.setString(6, orderBean.getRepairCost().toString());
			 			 
			 statement.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
		
	/**
	 * This method updates a record in db
	 * @param orderBean an object will be updated in db
	 * @return true - act complete successfully, false - act was broken
	 */
	public boolean updateOrder(OrderBean orderBean){
		PreparedStatement statement;
		try{
			statement = connection.prepareStatement(SQL_CHANGE_ORDER_BY_ID);
			statement.setString(1, orderBean.getComment());
			statement.setString(2, orderBean.getStatus());
			statement.setString(3, orderBean.getCost().toString());
			statement.setString(4, orderBean.getAdditionalCost().toString());
			statement.setString(5, orderBean.getRepairCost().toString());
			statement.setString(6, orderBean.getId().toString());
			statement.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * This method read OrderBean object from ResultSet object
	 * @param resultSet the object that is a result of query to DB
	 * @return OrderBean object
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	private OrderBean readOrderBean(ResultSet resultSet) throws NumberFormatException, SQLException {
		
		Integer id = Integer.parseInt(resultSet.getString("idorder"));
		Integer requestFK = Integer.parseInt(resultSet.getString("request_fk"));
		String comment = resultSet.getString("comment");
		String status = resultSet.getString("status");
		Integer cost = Integer.parseInt(resultSet.getString("cost"));
		Integer additionalCost = Integer.parseInt(resultSet.getString("additional_cost"));
		Integer repairCost = Integer.parseInt(resultSet.getString("repair_cost"));
		
		return new OrderBean(id, requestFK, comment, status, cost, additionalCost, repairCost);
	}
	
}
