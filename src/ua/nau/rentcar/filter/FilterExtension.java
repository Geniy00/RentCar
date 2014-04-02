package ua.nau.rentcar.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import ua.nau.rentcar.command.Command;
import ua.nau.rentcar.resource.Resource;

public class FilterExtension implements Filter {

	private FilterConfig config;
	private boolean active = false;
	
	@Override
	public void init(FilterConfig config) throws ServletException {

		this.config = config;
		
		String act = this.config.getInitParameter("active");
		if(act != null){
			active = act.equalsIgnoreCase("true");
		}

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		String context = req.getServletPath();
		if(active == true && context.equalsIgnoreCase("/index.jsp") == false){
			
			RequestDispatcher rd = 
					request.getServletContext().getRequestDispatcher(Resource.getStr(Command.PAGE_ERROR));
			rd.forward(request, response);
			
			
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

		config = null;

	}
}
