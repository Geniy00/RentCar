package ua.nau.rentcar.command.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.resource.Resource;

public class AddCar extends Command {
	
	public static final String MESSAGE_CAR_ADDED = "message.car.added";
	public static final String MESSAGE_CAR_NO_ADDED = "message.car.no.added";
	
	public static final String PAGE_CAR_ADD = "page.car.add";
	
	CarDAO carDAO = new CarDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer price;
		String mark, model, country, city, street, status;
		try{
			mark = request.getParameter(LoadCar.PARAM_CAR_MARK);
			model = request.getParameter(LoadCar.PARAM_CAR_MODEL);
			country = request.getParameter(LoadCar.PARAM_CAR_COUNTRY);
			city = request.getParameter(LoadCar.PARAM_CAR_CITY);
			street = request.getParameter(LoadCar.PARAM_CAR_STREET);
			price = Integer.parseInt(request.getParameter(LoadCar.PARAM_CAR_PRICE));
			status = request.getParameter(LoadCar.PARAM_CAR_STATUS);			
		} catch (NumberFormatException e) {
			setForward(Resource.getStr(PAGE_CAR_ADD));	
			return;
		}
		
		if(mark == null || mark.isEmpty() || model == null || model.isEmpty()  
				|| country == null || country.isEmpty() || city == null || city.isEmpty()
				|| street == null || street.isEmpty() || price == null || status == null
				|| status.isEmpty()){
			
			setMessage(Resource.getStr(MESSAGE_CAR_NO_ADDED));
			setForward(Resource.getStr(PAGE_CAR_ADD));
			logger.warn("some car parameters wasn't found");
			return;
		}

		CarBean car = new CarBean(0, mark, model, country, city, street, price, status);		
		if(carDAO.addCar(car) ){
			LoadCarList loadCarList = new LoadCarList();
			loadCarList.processRequest(request, response);
			setForward(loadCarList.getForward());
		} else {
			setMessage(Resource.getStr(MESSAGE_CAR_NO_ADDED));
			setForward(Resource.getStr(PAGE_CAR_ADD));
		}
		
	}

}
