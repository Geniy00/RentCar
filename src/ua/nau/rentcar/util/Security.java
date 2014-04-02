package ua.nau.rentcar.util;

import javax.servlet.http.HttpServletRequest;

import ua.nau.rentcar.command.CommandEnum;
import ua.nau.rentcar.command.authorization.Login;
import ua.nau.rentcar.model.beans.UserBean;

public class Security {
	
	/**
	 * Check permission executing a command for a request
	 * @param commandEnum command
	 * @param request a HttpServletRequest
	 * @return true - a command was permitted for this user, 
	 * 		false - a command was forbidden for this user
	 */
	public static boolean checkPermission(CommandEnum commandEnum, HttpServletRequest request){
		
		UserBean user = (UserBean)request.getSession().getAttribute(Login.ATTRIBUTE_USER);
		
		if(user == null){
			user = new UserBean();
			request.getSession().setAttribute(Login.ATTRIBUTE_USER, user); //XXX
		}
		
		UserTypeEnum currentUserType = user.getPermission();
		
		if(currentUserType.compareTo(commandEnum.getUserType()) >= 0){
			return true;
		} else return false;
	}
}
