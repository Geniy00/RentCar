package ua.nau.rentcar.command;

import javax.servlet.http.HttpServletRequest;

import ua.nau.rentcar.command.authorization.Login;
import ua.nau.rentcar.command.authorization.Logout;
import ua.nau.rentcar.command.authorization.SignUp;
import ua.nau.rentcar.command.car.AddCar;
import ua.nau.rentcar.command.car.EditCar;
import ua.nau.rentcar.command.car.LoadCar;
import ua.nau.rentcar.command.car.LoadCarList;
import ua.nau.rentcar.command.navigate.ToErrorPage;
import ua.nau.rentcar.command.navigate.WithoutParameter;
import ua.nau.rentcar.command.order.AddOrder;
import ua.nau.rentcar.command.order.EditOrder;
import ua.nau.rentcar.command.order.LoadOrder;
import ua.nau.rentcar.command.order.LoadOrderList;
import ua.nau.rentcar.command.request.AddRequest;
import ua.nau.rentcar.command.request.ExamineRequest;
import ua.nau.rentcar.command.request.LoadRequest;
import ua.nau.rentcar.command.request.LoadRequestList;
import ua.nau.rentcar.util.Security;

public class CommandFactory {
	
	public static final String PARAM_COMMAND = "command"; 
	
	public static Command getInstance(HttpServletRequest request){
		Command command = null;
		CommandEnum currentCommandType = getCommandEnum(request.getParameter(PARAM_COMMAND));
		if(Security.checkPermission(currentCommandType, request) == true){
			switch(currentCommandType){
			case WITHOUT_PARAMETER:
				command = new WithoutParameter();
				break;
			case SIGN_UP:
				command = new SignUp();
				break;
			case LOGIN:
				command = new Login();
				break;
			case LOGOUT:
				command = new Logout();
				break;
			case LOAD_CAR_LIST:
				command = new LoadCarList();
				break;
			case ADD_CAR:
				command = new AddCar();
				break;
			case LOAD_CAR:
				command = new LoadCar();
				break;
			case EDIT_CAR:
				command = new EditCar();
				break;
			case LOAD_REQUEST_LIST:
				command = new LoadRequestList();
				break;
			case LOAD_REQUEST:
				command = new LoadRequest();
				break;			
			case ADD_REQUEST:
				command = new AddRequest();
				break;
			case EXAMINE_REQUEST:
				command = new ExamineRequest();
				break;
			case LOAD_ORDER_LIST:
				command = new LoadOrderList();
				break;
			case LOAD_ORDER:
				command = new LoadOrder();
				break;
			case ADD_ORDER:
				command = new AddOrder();
				break;
			case EDIT_ORDER:
				command = new EditOrder();
				break;
			case UNKNOWN_COMMAND:
				command = new ToErrorPage();
				break;
			default:
				command = new ToErrorPage();
				break;
			}
		} else {
			command = new ToErrorPage();
		}
		return command;
	}
	
	/**
	 * This method create an instance of CommandEnum by String value
	 * If command incorrect then it returns UNKNOWN_COMMAND value
	 * @param commandName string command name
	 * @return CommandEnum that was got from string command name
	 */
	private static CommandEnum getCommandEnum(String commandName){
		CommandEnum commandEnum = null;
		
		if(commandName == null) {
			return CommandEnum.WITHOUT_PARAMETER;
		}
		
		try{
			commandEnum = CommandEnum.valueOf(commandName);
		} catch(Exception e) {
			commandEnum = CommandEnum.UNKNOWN_COMMAND;
		} 

		return commandEnum;
	}
}
