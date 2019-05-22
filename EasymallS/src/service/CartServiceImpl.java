package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CartDao;
import domain.Cart;
import domain.Prod;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao = null;

	@Transactional
	@Override
	public boolean addCart(int uid, Prod prod) {
		int pid = prod.getId();
		// Cart cart = cartDao.hasCart(uid, pid);
		Cart cart = cartDao.hasCart(new Cart(uid, pid));
		if (cart != null) {
			int id = cart.getId();
			return this.addCount(id);
		}
		return cartDao.addCart(new Cart(0, uid, prod.getId(), prod.getName(),
				prod.getPrice(), prod.getImgurl(), 1));
	}

	@Override
	public List<Cart> listCart(int uid) {
		return cartDao.listCart(uid);
	}

	@Override
	public boolean addCount(int id) {
		return cartDao.addCount(id);
	}

	@Override
	public boolean subCount(int id) {
		int count = cartDao.getCount(id);
		if (count == 1)
			return this.delCart(id);
		else
			return cartDao.subCount(id);
	}

	@Override
	public boolean delCart(int id) {
		return cartDao.delCart(id);
	}

}
