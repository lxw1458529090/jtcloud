package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import domain.Cart;
import domain.Category;
import domain.Prod;

@Repository
public interface ProdDao {

	public Category queryCid(String cname);

	public boolean addCategory(String cname);

	public boolean addProd(Prod prod);

	public List<Prod> listProd();

	public int countOfCid(int cid);

	public boolean delCategory(int cid);

	public boolean delProd(int id);

	public boolean updataProd(Prod prod);

	public List<Prod> kindProd(String cname);

	public Prod getProdById(int id);

	public List<Prod> getProdsByKW(String keyword);


}
