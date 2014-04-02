package ua.nau.rentcar.command.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.database.dao.OrderDAO;
import ua.nau.rentcar.database.dao.RequestDAO;
import ua.nau.rentcar.database.dao.UserDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.model.beans.OrderBean;
import ua.nau.rentcar.model.beans.RequestBean;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;

public class EditOrder extends Command {

	public static final String PARAM_COMMENT = "comment";
	public static final String PARAM_STATUS = "status";
	public static final String PARAM_COST = "cost";
	public static final String PARAM_ADDITIONAL_COST = "additional_cost";
	public static final String PARAM_REPAIR_COST = "repair_cost";
	
	public static final String ATTRIBUTE_USER_FK = "userfk";
	public static final String ATTRIBUTE_CAR_FK = "carfk";	
	public static final String ATTRIBUTE_ORDER = "order";
	
	public static final String PAGE_ORDER_EDIT = "page.order.edit";
	public static final String MESSAGE_ORDER_SAVED = "message.order.saved";
	
	private OrderDAO orderDAO = new OrderDAO();
	private RequestDAO requestDAO = new RequestDAO();
	private CarDAO carDAO = new CarDAO();
	private UserDAO userDAO = new UserDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * Obligatory parameters for GET method
		 */
		Integer id;
		try{
			id = Integer.parseInt(request.getParameter(LoadOrder.PARAM_ID_ORDER));
		} catch (Exception e) {
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		}
		
		OrderBean orderBean = orderDAO.getOrderById(id);
		RequestBean requestBean = requestDAO.getRequestById(orderBean.getRequestFK());
		CarBean carBean = carDAO.getCarById(requestBean.getCarFK());
		UserBean userBean = userDAO.getUserById(requestBean.getUserFK());
		request.setAttribute(ATTRIBUTE_ORDER, orderBean);
		request.setAttribute(ATTRIBUTE_CAR_FK, carBean);
		request.setAttribute(ATTRIBUTE_USER_FK, userBean);
		
		setForward(Resource.getStr(PAGE_ORDER_EDIT));
		/**
		 * Not obligatory parameters for GET method
		 * but it's obligatory for POST method
		 */
		//Integer requestFK;
		String comment, status;
		Integer cost, additionalCost, repairCost;
		try{
			comment = request.getParameter(PARAM_COMMENT);
			status = request.getParameter(PARAM_STATUS);
			cost = Integer.parseInt(request.getParameter(PARAM_COST));
			additionalCost = Integer.parseInt(request.getParameter(PARAM_ADDITIONAL_COST));
			repairCost =  Integer.parseInt(request.getParameter(PARAM_REPAIR_COST));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return;
		}
		
		if(id == null || comment == null || status == null
				|| cost == null || additionalCost == null
				|| repairCost == null){
			return;
		}
		
		orderBean = new OrderBean(id, 0, comment, status, cost, additionalCost, repairCost);
		if(orderDAO.updateOrder(orderBean) == true){
			setMessage(Resource.getStr(MESSAGE_ORDER_SAVED));
		}
		
		orderBean = orderDAO.getOrderById(orderBean.getId());
		
		if(orderBean != null){
			request.setAttribute(ATTRIBUTE_ORDER, orderBean);
			setForward(Resource.getStr(PAGE_ORDER_EDIT));
		} else {
			setForward(Resource.getStr(PAGE_ERROR));
		}
	}

}
