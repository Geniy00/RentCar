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

public class LoadOrder extends Command {

	public static final String PARAM_ID_ORDER = "idorder";
	
	public static final String ATTRIBUTE_ORDER = "order";
	public static final String ATTRIBUTE_USER = "userfk";
	public static final String ATTRIBUTE_CAR = "carfk";
	
	public static final String PAGE_ORDER = "page.order";
	public static final String PAGE_ORDER_LIST = "page.order.list";
	public static final String PAGE_ORDER_EDIT = "page.order.edit";
	
	private OrderDAO orderDAO = new OrderDAO();
	private RequestDAO requestDAO = new RequestDAO();
	private CarDAO carDAO = new CarDAO();
	private UserDAO userDAO = new UserDAO();
	
	//This method isn't used. You can see an order in Editcar
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer idorder;
		try{
			idorder = Integer.parseInt(request.getParameter(PARAM_ID_ORDER));
		} catch (Exception e) {
			// TODO: handle exception
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		}
		
		OrderBean orderBean = orderDAO.getOrderById(idorder);
		RequestBean requestBean = requestDAO.getRequestById(orderBean.getRequestFK());
		CarBean carBean = carDAO.getCarById(requestBean.getCarFK());
		UserBean userBean = userDAO.getUserById(requestBean.getUserFK());
		
		request.setAttribute(ATTRIBUTE_ORDER, orderBean);
		request.setAttribute(ATTRIBUTE_CAR, carBean);
		request.setAttribute(ATTRIBUTE_USER, userBean);
		setForward(Resource.getStr(PAGE_ORDER));
		
	}

}
