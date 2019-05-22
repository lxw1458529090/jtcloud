package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;

import service.UserService;

public class EncodingFilter implements Filter {

	private static String encode = null;

	// @Autowired
	// private UserService userService = null;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 处理请求乱码
		MyRequset myreq = new MyRequset((HttpServletRequest) req);

		// 处理应答乱码
		resp.setContentType("text/html;charset=" + encode);

		// System.out.println("userService:"+userService);

		// 放行
		chain.doFilter(myreq, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		encode = config.getServletContext().getInitParameter("encode");
	}

	private class MyRequset extends HttpServletRequestWrapper {

		public MyRequset(HttpServletRequest request) {
			super(request);
		}

		private boolean flag = true;

		@Override
		public String getParameter(String name) {
			String[] strs = this.getParameterValues(name);
			return strs == null ? null : strs[0];
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> map = super.getParameterMap();
			if (map != null && flag) {
				for (Map.Entry<String, String[]> entry : map.entrySet()) {
					String[] strings = entry.getValue();
					if (strings != null) {
						for (int i = 0; i < strings.length; i++) {
							try {
								strings[i] = new String(
										strings[i].getBytes("iso8859-1"),
										encode);
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
							}
						}
					}
				}
				flag = false;
			}
			return map;
		}

		@Override
		public Enumeration<String> getParameterNames() {
			return super.getParameterNames();
		}

		@Override
		public String[] getParameterValues(String name) {
			Map<String, String[]> map = this.getParameterMap();
			return map == null ? null : map.get(name);
		}

	}

}
