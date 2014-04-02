package ua.nau.rentcar.command.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.resource.Resource;

public class LoadCar extends Command {

	public static final String PARAM_CAR_ID = "idcar";
	public static final String PARAM_CAR_MARK = "mark";
	public static final String PARAM_CAR_MODEL = "model";
	public static final String PARAM_CAR_COUNTRY = "country";
	public static final String PARAM_CAR_CITY = "city";
	public static final String PARAM_CAR_STREET = "street";
	public static final String PARAM_CAR_PRICE = "price";
	public static final String PARAM_CAR_STATUS = "status";
	
	public static final String PAGE_LOAD_CAR = "page.car";
	
	public static final String ATTRIBUTE_CAR = "car";
	
	private CarDAO carDAO = new CarDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = null;;
		try{
			id = Integer.parseInt(request.getParameter(PARAM_CAR_ID));
		} catch (NumberFormatException e) {
			setForward(Resource.getStr(PAGE_ERROR));
			return;
		}
		
		CarBean car = carDAO.getCarById(id);
		
		if(car == null){
			setForward(Resource.getStr(PAGE_ERROR));
			logger.warn("Car id wasn't found");
			return;
		}
		
		request.setAttribute(ATTRIBUTE_CAR, car);
		setForward(Resource.getStr(PAGE_LOAD_CAR));
	}

}
