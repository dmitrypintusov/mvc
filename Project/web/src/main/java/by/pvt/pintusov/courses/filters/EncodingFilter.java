package by.pvt.pintusov.courses.filters;


import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter
 * @author pintusov
 * @version 1.0
 */

public class EncodingFilter implements Filter {
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
		request.setCharacterEncoding("UTF-8");
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {}
}
