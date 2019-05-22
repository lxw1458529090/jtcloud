package web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import service.ProdService;
import service.UserService;
import util.MD5Util;
import util.VerifyCode;

import domain.Prod;
import domain.User;

@Controller
public class UserController {
	@Autowired
	private UserService userService = null;

	// @Autowired
	// private ProdService prodService = null;

	@RequestMapping("/regist.action")
	public String regist(User user, Model model) {
		// ??????
		model.addAttribute("user", user);
		// ?§Ø????????????
		boolean flag = userService.hasUser(user.getUsername());
		// System.out.println("-----flag:"+flag);
		// ??????????
		if (flag) {
			model.addAttribute("errMsg", "???????????");
			return "regist";
		}
		flag = userService.regist(user);
		// ?????
		if (flag)
			return "redirect:/index.jsp";

		model.addAttribute("errMsg", "????????????????");
		return "regist";
	}

	@RequestMapping("/login.action")
	public String login(User user, String remname, String autologin,
			HttpServletRequest req, HttpServletResponse resp) {
		// if(user==null)
		// return "login";
		user.setPassword(MD5Util.md5(user.getPassword()));
		req.setAttribute("username", user.getUsername());
		User user2 = userService.login(user);
		if (user2 != null) {
			if ("true".equals(remname)) {
				Cookie cookie = new Cookie("remname", URLEncoder.encode(user
						.getUsername()));
				cookie.setMaxAge(60 * 60 * 24 * 30);
				cookie.setPath("/");
				resp.addCookie(cookie);
			}
			if ("true".equals(autologin)) {
				Cookie cookie = new Cookie("autologin", URLEncoder.encode(user
						.getUsername() + "#" + user.getPassword()));
				cookie.setMaxAge(60 * 60 * 24 * 30);
				cookie.setPath("/");
				resp.addCookie(cookie);
			}

			req.getSession().setAttribute("user", user2);
			return "index";
		} else {
			Cookie cookie = new Cookie("remname", "");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			resp.addCookie(cookie);
			req.setAttribute("errMsg", "??????????????");
			return "login";
		}
	}

	@RequestMapping("/logout.action")
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
		Cookie cookie = new Cookie("autologin", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		resp.addCookie(cookie);
		return "index";
	}

	@RequestMapping("/verify.action")
	public void verify(HttpServletResponse resp) {
		VerifyCode ver = new VerifyCode();
		try {
			ver.drawImage(resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/hasUsername.action")
	public void hasUsername(String username, HttpServletResponse resp) {
		boolean flag = userService.hasUser(username);
		String msg = flag ? "???????????" : "??????????";
		try {
			resp.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/checkLogin.action")
	public void checkLogin(HttpServletRequest req){
		HttpSession sess = req.getSession(false);
//		if(sess==null||sess.getAttribute("user")==null)
			
	}

	// @RequestMapping("/userProdList.action")
	// public String prodList(Model model) {
	// List<Prod> prods = prodService.getProdList();
	// model.addAttribute("list", prods);
	// return "prod_list";
	// }

}
