package ua.nau.rentcar.command.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.command.authorization.Login;
import ua.nau.rentcar.database.dao.OrderDAO;
import ua.nau.rentcar.model.beans.OrderBean;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;
import ua.nau.rentcar.util.UserTypeEnum;

public class LoadOrderList extends Command {

	public static final String ATTRIBUTE_ORDERS = "orders";
	public static final String PAGE_ORDER_LIST = "page.order.list";
	
	private OrderDAO orderDAO = new OrderDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserBean userBean = (UserBean)request.getSession().getAttribute(Login.ATTRIBUTE_USER);		
		OrderBean[] orderBeans;
		
		if(userBean.getPermission().compareTo(UserTypeEnum.ADMIN) >= 0){		
			orderBeans = orderDAO.getAllOrders();
		} else {
			orderBeans = orderDAO.getOrderByUserId(userBean.getId());
		}
		
		request.setAttribute(ATTRIBUTE_ORDERS, orderBeans);
		setForward(Resource.getStr(PAGE_ORDER_LIST));
	}

}
