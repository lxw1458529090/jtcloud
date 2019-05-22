package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.CartService;
import service.ProdService;

import domain.Cart;
import domain.Prod;
import domain.User;

@Controller
public class CartController {

	@Autowired
	ProdService prodService = null;

	@Autowired
	CartService cartService = null;
	
	@RequestMapping("/delCart.action")
	public String delCart(int id){
		boolean flag = cartService.delCart(id);
		System.out.println(123);
		return "redirect:/listCart.action";
	}

	@RequestMapping("/myCart.action")
	public void myCart(HttpServletRequest req, HttpServletResponse resp) {
		boolean flag = this.checkLogin(req);
		if (!flag) {
			try {
				resp.getWriter().write("请先登录！");
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// try {
		// req.getRequestDispatcher("/listCart.action").forward(req, resp);
		// } catch (ServletException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}

	// @RequestMapping("/subCount.action")
	// public void subCount(int id, HttpServletResponse resp) {
	// boolean flag = cartService.subCount(id);
	// try {
	// resp.getWriter().write(flag ? "操作成功！" : "操作失败！");
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	@RequestMapping("/subCount.action")
	public String subCount(int id, HttpServletResponse resp) {
		boolean flag = cartService.subCount(id);
		return "redirect:/listCart.action";
	}
	

	@RequestMapping("/addCount.action")
	public void addCount(int id, HttpServletResponse resp) {
		boolean flag = cartService.addCount(id);
		try {
			resp.getWriter().write(flag ? "添加成功！" : "添加失败！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/listCart.action")
	public String listCart(HttpServletRequest req) {
		// if(!checkLogin(req))
		// return "login";
		int uid = ((User) req.getSession().getAttribute("user")).getId();
		List<Cart> list = cartService.listCart(uid);
		req.setAttribute("cartList", list);
		return "cart";
	}

	@RequestMapping("/addToCart.action")
	public void addToCart(int id, HttpServletRequest req,
			HttpServletResponse resp) {
		// 判断有无登录
		HttpSession session = req.getSession(false);
		// 未登录 跳转登录页面
		if (session == null || session.getAttribute("user") == null) {
			try {
				resp.getWriter().write("请先登录！");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		// if(!this.checkLogin(req)){
		// try {
		// resp.getWriter().write("请先登录！");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// return;
		// }
		// 已登录，查看该用户有无该商品

		// 将订单信息插入购物车表
		session = req.getSession();
		int uid = ((User) session.getAttribute("user")).getId();
		Prod prod = prodService.getProdById(id);
		boolean flag = cartService.addCart(uid, prod);

		try {
			if (flag)
				resp.getWriter().write("添加成功！");
			else
				resp.getWriter().write("添加失败！");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println(pid);

	}

	private boolean checkLogin(HttpServletRequest req) {
		HttpSession sess = req.getSession(false);
		if (sess == null || sess.getAttribute("user") == null)
			return false;
		else
			return true;
	}
}
