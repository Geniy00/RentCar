package ua.nau.rentcar.command.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.command.authorization.Login;
import ua.nau.rentcar.database.dao.RequestDAO;
import ua.nau.rentcar.model.beans.RequestBean;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;
import ua.nau.rentcar.util.UserTypeEnum;

public class LoadRequestList extends Command {

	public static final String ATTRIBUTE_REQUESTS = "requests";
	public static final String PAGE_REQUEST_LIST = "page.request.list";

	private RequestDAO requestDAO = new RequestDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserBean user = (UserBean)request.getSession().getAttribute(Login.ATTRIBUTE_USER);
		RequestBean[] requestBeans;
		
		if(user.getPermission().compareTo(UserTypeEnum.ADMIN) >= 0){
			requestBeans = requestDAO.getAllRequests();
		} else {
			requestBeans = requestDAO.getRequestByUserId(user.getId());
		}

		request.setAttribute(ATTRIBUTE_REQUESTS, requestBeans);
		setForward(Resource.getStr(PAGE_REQUEST_LIST));
	}

}
