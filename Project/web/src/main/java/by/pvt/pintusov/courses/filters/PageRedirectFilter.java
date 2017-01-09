package by.pvt.pintusov.courses.filters;

import by.pvt.pintusov.courses.constants.PagePath;
import by.pvt.pintusov.courses.managers.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Page redirect filter
 * @author pintusov
 * @version 1.0
 */

public class PageRedirectFilter implements Filter {
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
		httpResponse.sendRedirect(httpRequest.getContextPath() + ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH));
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
