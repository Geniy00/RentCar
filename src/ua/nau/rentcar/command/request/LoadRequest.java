package ua.nau.rentcar.command.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.database.dao.RequestDAO;
import ua.nau.rentcar.database.dao.UserDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.model.beans.RequestBean;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;

public class LoadRequest extends Command {

	public static final String PARAM_REQUEST_ID = "idrequest";
	public static final String PARAM_USER_FK = "user_fk";
	public static final String PARAM_CAR_FK = "car_fk";
	public static final String PARAM_BEGIN_DATE = "begin_date";
	public static final String PARAM_END_DATE = "end_date";
	public static final String PARAM_PASSPORT = "passport";
	public static final String PARAM_REQUEST_MESSAGE = "request_message";
	public static final String PARAM_RESPONSE_MESSAGE = "response_message";
	public static final String PARAM_COST = "cost";
	public static final String PARAM_STATUS = "status";
	
	public static final String ATTRIBUTE_REQUEST = "request";
	public static final String ATTRIBUTE_USER = "userfk";
	public static final String ATTRIBUTE_CAR = "carfk";
	
	public static final String PAGE_REQUEST = "page.request";
	
	private RequestDAO requestDAO = new RequestDAO();
	private UserDAO userDAO = new UserDAO();
	private CarDAO carDAO = new CarDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer id;
		try{
			id = Integer.parseInt(request.getParameter(PARAM_REQUEST_ID));
		} catch (NumberFormatException e) {
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		}
		
		RequestBean requestBean = requestDAO.getRequestById(id);
		
		if(requestBean == null){
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		}
		
		UserBean user = userDAO.getUserById(requestBean.getUserFK());
		CarBean car = carDAO.getCarById(requestBean.getCarFK());
		
		request.setAttribute(ATTRIBUTE_REQUEST, requestBean);
		request.setAttribute(ATTRIBUTE_USER, user);
		request.setAttribute(ATTRIBUTE_CAR, car);
		setForward(Resource.getStr(PAGE_REQUEST));
		
	}

}
