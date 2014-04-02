package ua.nau.rentcar.command.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.resource.Resource;

public class Logout extends Command {

	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession(false).removeAttribute("user");
		request.getSession(false).invalidate();
		setForward(Resource.getStr(Login.PAGE_LOGIN));
	}

}
