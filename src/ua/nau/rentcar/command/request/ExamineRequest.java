package ua.nau.rentcar.command.request;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.command.order.AddOrder;
import ua.nau.rentcar.database.dao.RequestDAO;
import ua.nau.rentcar.resource.Resource;

public class ExamineRequest extends Command {

	@Override
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Integer id;
		String responseMessage;
		String action;
		try {
			id = Integer.parseInt(request.getParameter("idrequest"));
			responseMessage = request.getParameter("response_message");
			action = request.getParameter("action");
			
			if(responseMessage.isEmpty() || action.isEmpty()){
				throw new Exception();
			}
			
		} catch (NumberFormatException e){
			setForward(Resource.getStr(PAGE_ERROR));
			setMessage(Resource.getStr(MESSAGA_ACTION_INTERRUPTED));
			return;
		} catch (Exception e) {
			// TODO: handle exception
			LoadRequest loadRequest = new LoadRequest();
			loadRequest.processRequest(request, response);			
			setForward(loadRequest.getForward());
			setMessage(Resource.getStr(MESSAGA_ACTION_INTERRUPTED));
			return;
		}
		
		RequestDAO requestDAO = new RequestDAO();
		
		boolean done = false;
		if(action.equalsIgnoreCase("accept")){
			done = requestDAO.acceptRequest(id, responseMessage);
			
			AddOrder addOrder = new AddOrder();
			addOrder.processRequest(request, response);
			setForward(addOrder.getForward());
			setMessage(addOrder.getMessage());
		} else if (action.equalsIgnoreCase("refuse")){
			done = requestDAO.refuseRequest(id, responseMessage);
			
			//Make load request
			LoadRequest loadRequest = new LoadRequest();
			loadRequest.processRequest(request, response);
			setForward(loadRequest.getForward());
			if(done) {
				setMessage(Resource.getStr(MESSAGE_ACTION_DONE));
			}
		}

		
	}
}
