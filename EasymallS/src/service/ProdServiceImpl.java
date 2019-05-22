package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ProdDao;
import domain.Cart;
import domain.Category;
import domain.Prod;

//@Scope("prototype")
@Service
public class ProdServiceImpl implements ProdService {
	@Autowired
	ProdDao prodDao = null;

	@Transactional
	@Override
	public boolean addProd(Prod prod) {
		System.err.println(prod.getCname());
		Category category = prodDao.queryCid(prod.getCname());
		if (category == null) {
			boolean flag = prodDao.addCategory(prod.getCname());
			if (flag)
				category = prodDao.queryCid(prod.getCname());
			else
				return false;
		}
		prod.setCid(category.getId());
		// System.out.println("_____"+prod);
		return prodDao.addProd(prod);
	}

	@Override
	public List<Prod> getProdList() {
		return prodDao.listProd();
	}

	@Override
	public boolean delProd(Prod prod) {
		// 检查是否最后一件商品
		int count = prodDao.countOfCid(prod.getCid());
		boolean flag = true;
		if (count == 1) {
			flag = prodDao.delCategory(prod.getCid()) && flag;
		}
		flag = prodDao.delProd(prod.getId()) && flag;
		return flag;
	}

	@Override
	public boolean updataProd(Prod prod) {
		return prodDao.updataProd(prod);
	}

	@Override
	public List<Prod> getKindProd(String cname) {

		return prodDao.kindProd(cname);
	}

	@Override
	public Prod getProdById(int id) {
		return prodDao.getProdById(id);
	}

	@Override
	public List<Prod> getProdByKW(String keyword) {
		keyword = "%" + keyword + "%";
		return this.prodDao.getProdsByKW(keyword);
	}

}
