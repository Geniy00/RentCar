package ua.nau.rentcar.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * The abstract class that realize view for all command classes
 * @author Geniy
 *
 */
public abstract class Command {
	
	public static final String MSG_DATABASE_ERROR = "error.database.fail";
	public static final String PAGE_ERROR = "page.error";
	public static final String PAGE_MAIN = "page.main";
	public static final String MESSAGA_ACTION_INTERRUPTED = "message.action.interrupted"; 
	public static final String MESSAGE_ACTION_DONE = "message.action.done";
	
	protected static Logger logger = Logger.getLogger(Command.class);
	
	/**
	 * The address where will be forwarded after the command will be executed
	 */
	private String forward;	
	/**
	 * The message which will be printed in jsp
	 */
	private String message;
	
	public String getForward() {
		return forward;
	}

	public void setForward(String forward) {
		this.forward = forward;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/**
	 * The method that describing all actions for some command
	 * @param request a HttpServletRequest object
	 * @param response a HttpServletResponse object
	 * @throws ServletException a ServletException exception
	 * @throws IOException a IOEXception exception
	 */
	public abstract void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;

}
