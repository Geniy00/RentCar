package ua.nau.rentcar.command.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.resource.Resource;

public class EditCar extends Command {

	public static final String MESSAGE_CAR_SAVED = "message.car.saved";
	public static final String MESSAGE_CAR_NO_SAVED = "message.car.saved";
	public static final String PAGE_CAR_EDIT = "page.car.edit";
	
	
	private CarDAO carDAO = new CarDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		/**
		 * Obligatory parameters for GET method
		 */
		Integer id;	
		try{
			id = Integer.parseInt(request.getParameter(LoadCar.PARAM_CAR_ID));
		} catch (NumberFormatException e) {
			setForward(Resource.getStr(PAGE_ERROR));
			logger.warn("Parameter car id wasn't found");
			return;
		}
		
		CarBean car = carDAO.getCarById(id);
		if(car == null){
			logger.warn("car that has carid " + id + " wasn't found");
			return;
		}
		request.setAttribute(LoadCar.ATTRIBUTE_CAR, car);
		setForward(Resource.getStr(PAGE_CAR_EDIT));
	
		/**
		 * Not obligatory parameters for GET method
		 * but it's obligatory for POST method
		 */
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
			// TODO: handle exception	
			return;
		}
		
		
		if(mark == null || mark.isEmpty() || model == null || model.isEmpty()  
				|| country == null || country.isEmpty() || city == null || city.isEmpty()
				|| street == null || street.isEmpty() || price == null || status == null
				|| status.isEmpty()){
			
			setMessage(Resource.getStr(MESSAGE_CAR_NO_SAVED));
			logger.warn("some car parameters wasn't found");
			return;
		}
		
		
		car = new CarBean(id, mark, model, country, city, street, price, status);		
		if(carDAO.updateCar(car) == true){
			setMessage(Resource.getStr(MESSAGE_CAR_SAVED));
		}
			
		car = carDAO.getCarById(id);
		
		if(car != null){
			request.setAttribute(LoadCar.ATTRIBUTE_CAR, car);
			setForward(Resource.getStr(PAGE_CAR_EDIT));
		} else{
			setForward(Resource.getStr(PAGE_ERROR));			
		}
	}
}
