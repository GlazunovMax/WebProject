package by.epam.javawebtraining.glazunov.webproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class CharsetFilter
 */
public class CharsetFilter implements Filter {
	private String encoding;

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("requestEncoding");
		if (encoding == null) {
			encoding = "utf-8";
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(encoding);

		response.setContentType("text/html;charset=" + encoding);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.encoding = null;
	}

}
