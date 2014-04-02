package ua.nau.rentcar.command.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.CarDAO;
import ua.nau.rentcar.model.beans.CarBean;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;
import ua.nau.rentcar.util.UserTypeEnum;

public class LoadCarList extends Command {

	public static final String ATTRIBUTE_CARS = "cars";
	
	public static final String PAGE_CAR_LIST = "page.car.list";
	
	private CarDAO carDAO = new CarDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		UserBean user = (UserBean)request.getSession().getAttribute("user");
		
		boolean unavailable = user.getPermission().equals(UserTypeEnum.ADMIN);
		
		CarBean[] cars = carDAO.getAllCars(unavailable);
		
		request.setAttribute(ATTRIBUTE_CARS, cars);		
		
		setForward(Resource.getStr(PAGE_CAR_LIST));
	}

}
