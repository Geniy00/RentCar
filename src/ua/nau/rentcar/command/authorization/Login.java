package ua.nau.rentcar.command.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.UserDAO;
import ua.nau.rentcar.model.beans.UserBean;
import ua.nau.rentcar.resource.Resource;
import ua.nau.rentcar.util.UserTypeEnum;

/**
 * Login command
 * Authentication in DB by login and password.
 * @author Geniy
 *
 */
public class Login extends Command {

	public static final String PARAM_LOGIN = "login";
	public static final String PARAM_PASSWORD = "password";
	public static final String ATTRIBUTE_USER = "user";	
	public static final String PAGE_LOGIN = "page.login";
	public static final String MESSAGE_ERROR = "message.login.error";
	
	/**
	 * This will be created if login and password are accepted
	 */
	private UserBean user;
	/**
	 * It realizes authentication in DB method
	 */
	private UserDAO userDAO = new UserDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter(PARAM_LOGIN);
		String password = request.getParameter(PARAM_PASSWORD);
		
		user = (UserBean)request.getSession().getAttribute(ATTRIBUTE_USER);
		request.getSession().invalidate();
		
		if(login != null && password != null &&
			login.length() > 0 && password.length() > 0){			
			user = userDAO.getUserByLoginPassword(login, password);			
		}
		
		
		
		if (user != null && user.getPermission().compareTo(UserTypeEnum.USER) >= 0){
			request.getSession().setAttribute(ATTRIBUTE_USER, user);
			setForward(Resource.getStr(PAGE_MAIN));	
			logger.debug("User loggined");
		} else {
			setForward(Resource.getStr(PAGE_LOGIN));
			setMessage(Resource.getStr(MESSAGE_ERROR));
			logger.debug("A login/password incorrect");
		}
	}

}
