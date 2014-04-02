package ua.nau.rentcar.command.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.command.authorization.Login;
import ua.nau.rentcar.command.car.LoadCar;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.database.dao.RequestDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.model.beans.RequestBean;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;

public class AddRequest extends Command {

	public static final String PAGE_REQUEST_ADD = "page.request.add";
	
	public static final String MESSAGE_REQUEST_ADDED = "message.request.added";
	public static final String MESSAGE_REQUEST_NO_ADDED = "message.request.no.added";
	
	
	private CarDAO carDAO = new CarDAO();
	private RequestDAO requestDAO = new RequestDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Obligatory parameters for GET method
		 */
		Integer idcar;	
		CarBean carBean = null;
		try{
			idcar = Integer.parseInt(request.getParameter(LoadCar.PARAM_CAR_ID));	
			carBean = carDAO.getCarById(idcar);
		} catch (NumberFormatException e) {
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		} 
			
		if(carBean == null){
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		}
						
		
		UserBean userBean = (UserBean)request.getSession().getAttribute(Login.ATTRIBUTE_USER);
		RequestBean requestBean = new RequestBean();
		requestBean.setUserFK(userBean.getId());
		requestBean.setCarFK(carBean.getId());
		requestBean.setCost(carBean.getPrice());
		
		request.setAttribute(LoadCar.ATTRIBUTE_CAR, carBean);
		request.setAttribute(LoadRequest.ATTRIBUTE_REQUEST, requestBean);
		
		setForward(Resource.getStr(PAGE_REQUEST_ADD));
		
		/**
		 * Not obligatory parameters for GET method
		 * but it's obligatory for POST method
		 */		
		String beginDate = null, endDate = null;
		String passport = null, requestMessage = null;
		Integer cost = null;
		try{			
			//DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			//dateFormat.setLenient(false);			
			beginDate = request.getParameter(LoadRequest.PARAM_BEGIN_DATE);			
			endDate = request.getParameter(LoadRequest.PARAM_END_DATE);			
			passport = request.getParameter(LoadRequest.PARAM_PASSPORT);			
			requestMessage = request.getParameter(LoadRequest.PARAM_REQUEST_MESSAGE);
			cost =  carBean.getPrice();
		} catch (Exception e) {
			return;
		}
		
		
		//RequestBean requestBean = null;
		if(idcar == null || beginDate == null || beginDate.isEmpty() 
				|| endDate == null || endDate.isEmpty() || passport == null 
				|| passport.isEmpty() || requestMessage == null || requestMessage.isEmpty()
				|| beginDate.length() != 10 || endDate.length() != 10){
			
			setForward(Resource.getStr(PAGE_REQUEST_ADD));
			return;
		}
			
		requestBean = new RequestBean(new Integer(0), userBean.getId(), carBean.getId(), beginDate, endDate, 
				passport, requestMessage, "", cost, "PENDING");
		
		if(requestDAO.addRequest(requestBean) == true){
			requestBean = requestDAO.getRequestByData(requestBean);	
			request.setAttribute(LoadRequest.ATTRIBUTE_REQUEST, requestBean);
			setMessage(Resource.getStr(MESSAGE_REQUEST_ADDED));
		} else {
			setMessage(Resource.getStr(MESSAGE_REQUEST_NO_ADDED));
		}
		
		
		
	}

}
