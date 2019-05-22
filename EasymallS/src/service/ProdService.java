package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import domain.Cart;
import domain.Prod;

public interface ProdService {

	boolean addProd(Prod prod);

	List<Prod> getProdList();

	@Transactional
	boolean delProd(Prod prod);

	boolean updataProd(Prod prod);

	List<Prod> getKindProd(String cname);

	Prod getProdById(int id);

	List<Prod> getProdByKW(String keyword);



}
