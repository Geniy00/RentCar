package ua.nau.rentcar.command.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.command.request.LoadRequest;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.database.dao.OrderDAO;
import ua.nau.rentcar.database.dao.RequestDAO;
import ua.nau.rentcar.database.dao.UserDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.model.beans.OrderBean;
import ua.nau.rentcar.model.beans.RequestBean;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;

public class AddOrder extends Command {

	public static final String PARAM_ORDER_ID = "orderid";
	public static final String PARAM_REQUEST_FK = "request_fk";
	public static final String PARAM_COMMENT = "comment";
	public static final String PARAM_STATUS = "status";
	public static final String PARAM_COST = "cost";
	public static final String PARAM_ADDITIONAL_COST = "additional_cost";
	public static final String PARAM_REPAIR_COST = "repair_cost";

	public static final String MESSAGE_ORDER_CREATED = "message.order.created";
	public static final String MESSAGE_ORDER_NOT_CREATED ="message.order.not.created";
	
	private RequestDAO requestDAO = new RequestDAO();
	private OrderDAO orderDAO = new OrderDAO();

	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer idrequest;
		//String responseMessage;
		try {
			idrequest = Integer.parseInt(request.getParameter(LoadRequest.PARAM_REQUEST_ID));
			//responseMessage = request.getParameter(LoadRequest.PARAM_RESPONSE_MESSAGE);
		} catch(NumberFormatException e){
			setMessage(Resource.getStr(MESSAGE_ORDER_NOT_CREATED));
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		}

		
		RequestBean requestBean = requestDAO.getRequestById(idrequest);
		
		OrderBean orderBean = new OrderBean();
		orderBean.setRequestFK(requestBean.getId());
		orderBean.setCost(requestBean.getCost());
		orderDAO.addOrder(orderBean);
		
		request.setAttribute(LoadRequest.PARAM_REQUEST_ID, requestBean.getId());
		setForward(Resource.getStr(LoadRequest.PAGE_REQUEST));
		setMessage(Resource.getStr(MESSAGE_ORDER_CREATED));
		
		
		/**
		 * Add attribute to autofill fields in request.jsp
		 */
		UserBean user = new UserDAO().getUserById(requestBean.getUserFK());
		CarBean car = new CarDAO().getCarById(requestBean.getCarFK());		
		request.setAttribute(LoadRequest.ATTRIBUTE_REQUEST, requestBean);
		request.setAttribute(LoadRequest.ATTRIBUTE_USER, user);
		request.setAttribute(LoadRequest.ATTRIBUTE_CAR, car);
		
	}

}
