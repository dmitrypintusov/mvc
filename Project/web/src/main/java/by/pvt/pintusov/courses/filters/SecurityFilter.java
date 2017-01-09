package by.pvt.pintusov.courses.filters;

import by.pvt.pintusov.courses.commands.factory.CommandType;
import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.managers.ConfigurationManager;
import by.pvt.pintusov.courses.utils.RequestParameterParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Security filter
 * @author pintusov
 * @version 1.0
 */

public class SecurityFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	/**
	 * Execute filter
	 * @param request - from server
	 * @param response - to server
	 * @param filterChain - use filter chain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		UserType type = RequestParameterParser.getUserType(httpRequest);
		try {
			CommandType commandType = RequestParameterParser.getCommandType(httpRequest);
			if (type == null) {
				if (commandType == CommandType.LOGIN) {
					filterChain.doFilter(request, response);
				} else if (commandType == CommandType.GO_TO_REGISTRATION) {
					filterChain.doFilter(request, response);
				} else if (commandType == CommandType.REGISTRATION) {
					filterChain.doFilter(request, response);
				} else {
					String page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
					RequestDispatcher dispatcher = request.getRequestDispatcher(page);
					dispatcher.forward(httpRequest, httpResponse);
					session.invalidate();
				}
			} else {
				filterChain.doFilter(request, response);
			}
		} catch (IllegalArgumentException e) {
			String page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(httpRequest, httpResponse);
		}
	}

	@Override
	public void destroy() {}
}
