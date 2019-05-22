package filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import domain.User;

import service.UserService;
import service.UserServiceImpl;
import util.BaseFactory;

@Component("autoLoginFilter")
public class AutoLoginFilter implements Filter {

	@Autowired(required = true)
	private UserService userService;

	// BaseFactory.getFactory().getInstence(
	// UserService.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// �ж��Ƿ��¼
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);

		// System.out.println("userService:" + userService);

		if (session == null || session.getAttribute("user") == null) {
			// �ж�30�����Ƿ�ѡ��30���Զ���¼
			Cookie[] cookies = req.getCookies();
			String[] strs = null;
			if (cookies != null) {
				for (Cookie c : cookies) {
					if ("autologin".equals(c.getName()))
						strs = URLDecoder.decode(c.getValue()).split("#");
				}
			}

			if (strs != null) {
				// ִ���Զ���¼
				User user = userService.login(new User(0, strs[0], strs[1],
						null, null));
				req.getSession().setAttribute("user", user);
				System.out.println(strs[0] + "�Զ���¼�ɹ���");
			}
		}
		// ����
		chain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
