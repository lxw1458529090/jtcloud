package dao;

import org.springframework.stereotype.Repository;

import domain.User;
@Repository
public interface UserDao {

	User queryUser(String username);

	boolean addUser(User user);

	User login(User user);

}
