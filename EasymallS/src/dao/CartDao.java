package dao;

import java.util.List;

import domain.Cart;

public interface CartDao {

	public boolean addCart(Cart cart);

	public List<Cart> listCart(int uid);

	// public Cart hasCart(int uid, int pid);

	public boolean addCount(int id);

	public Cart hasCart(Cart cart);

	public int getCount(int id);

	public boolean delCart(int id);

	public boolean subCount(int id);

}
