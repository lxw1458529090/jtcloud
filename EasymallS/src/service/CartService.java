package service;

import java.util.List;

import domain.Cart;
import domain.Prod;

public interface CartService {

	public boolean addCart(int uid, Prod prod);

	public List<Cart> listCart(int uid);

	public boolean addCount(int id);

	public boolean subCount(int id);

	public boolean delCart(int id);

}
