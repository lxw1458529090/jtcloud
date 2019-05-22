package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import service.ProdService;

import domain.Prod;
import domain.User;

@Controller
public class ProdController {
	@Autowired
	ProdService prodService = null;

	@RequestMapping("/searchProd.action")
	public String searchProd(Model model, String keyword) {
		// System.out.println("keyword:"+keyword);
		List<Prod> prods = this.prodService.getProdByKW(keyword);
		model.addAttribute("list", prods);
		model.addAttribute("keyword", keyword);
		return "prod_list";
	}

	@RequestMapping("/addProd.action")
	public String addProd(Prod prod, MultipartFile fx, HttpServletRequest req)
			throws IOException {
		String realpath = req.getRealPath("/WEB-INF/upload");
		// 获取文件名
		String fileName = fx.getOriginalFilename();
		// 解决IE bug
		if (fileName.contains("\\")) {
			fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
		}
		// 解决文件重名问题
		fileName = UUID.randomUUID().toString() + prod.getName() + fileName;
		// 解决单个目录下文件过多的问题
		String filePath = Integer.toHexString(fileName.hashCode());

		StringBuilder sb = new StringBuilder("/");
		for (int i = 0; i < 3; i++) {
			sb.append(filePath.charAt(i)).append("/");
		}
		filePath = realpath + sb.toString();
		// 文件上传
		FileUtils.writeByteArrayToFile(new File(filePath + fileName),
				fx.getBytes());
		// 设置图片相当路径
		prod.setImgurl("/WEB-INF/upload" + sb.toString() + fileName);
		// 执行添加商品逻辑
		boolean flag = prodService.addProd(prod);
		// 添加失败 提示错误信息
		if (!flag) {
			req.setAttribute("errMsg", "添加失败！");
			return "/backend/manageAddProd";
		}

		return "/backend/_right";
		// return "refresh:2;/backend/_right";
	}

	@RequestMapping("/prodList.action")
	public String prodList(Model model, String url) {
		List<Prod> prods = prodService.getProdList();
		model.addAttribute("list", prods);
		return url;
	}

	@RequestMapping("/prodImg.action")
	public void prodImg(String imgurl, HttpServletRequest req,
			HttpServletResponse resp) {
		// System.err.println("imgurl1:" + imgurl);
		// System.err.println("imgurl2:" + req.getParameter("imgurl"));
		// try {
		// imgurl = new String(imgurl.getBytes("iso8859-1"), "utf-8");
		// } catch (UnsupportedEncodingException e1) {
		// e1.printStackTrace();
		// }
		// System.err.println("imgurl3:" + imgurl);
		String path = req.getRealPath(imgurl);
		OutputStream out = null;
		InputStream in = null;
		try {
			out = resp.getOutputStream();
			in = new FileInputStream(path);
			byte[] by = new byte[1024];
			int len;
			while ((len = in.read(by)) != -1) {
				out.write(by, 0, len);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/delProd.action")
	public String delProd(Prod prod, Model model) {
		boolean flag = prodService.delProd(prod);
		if (!flag)
			model.addAttribute("errMsg", "删除失败！");
		return "forward:/prodList.action?url=/backend/manageProdList";
	}

	@RequestMapping("/updataPnum.action")
	public void updataPnum(Prod prod, HttpServletResponse resp) {
		// System.out.println(prod+"________");
		boolean flag = prodService.updataProd(prod);
		String msg = flag ? "修改成功!" : "修改失败!";
		try {
			// resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/kindProd.action")
	public String kindProd(String cname, Model model) {
		List<Prod> list = prodService.getKindProd(cname);
		model.addAttribute("list", list);
		System.out.println("cname:" + cname);
		return "prod_list";
	}
}
