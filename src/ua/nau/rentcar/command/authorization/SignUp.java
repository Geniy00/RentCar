package ua.nau.rentcar.command.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.database.dao.UserDAO;
import ua.nau.rentcar.resource.Resource;

public class SignUp extends Command {

	public static final String MESSAGE_SIGN_UP_ERROR = "message.sign.up.error";
	public static final String PAGE_SIGN_UP = "page.sign.up";
	
	private UserDAO userDAO = new UserDAO();
	
	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		String login;
		String firstName, lastName;
		String password, confirmPassword;
		try{
			login = request.getParameter("login");
			password = request.getParameter("password");
			confirmPassword = request.getParameter("confirm_password");
			firstName = request.getParameter("first_name");
			lastName = request.getParameter("last_name");
		} catch (Exception e) {
			setForward(Resource.getStr(PAGE_SIGN_UP));
			return;
		}
		
		if(login == null || login.length() < 4 || password == null || password.length() < 5
			|| confirmPassword == null ||  password.equals(confirmPassword) == false){
			
			setForward(Resource.getStr(PAGE_SIGN_UP));			
			return;
		}
		
		if(userDAO.addUser(login, password, "USER", firstName, lastName)){
			setForward(Resource.getStr(Login.PAGE_LOGIN));
			setMessage("The user sign up successfully. Please enter you login/password");
			return;
		} else{
			setForward(Resource.getStr(PAGE_SIGN_UP));
			setMessage(Resource.getStr(MESSAGE_SIGN_UP_ERROR));
		}
		

	}

}
