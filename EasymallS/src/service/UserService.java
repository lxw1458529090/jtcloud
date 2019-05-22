package service;

import org.springframework.transaction.annotation.Transactional;

import domain.User;

public interface UserService {
	@Transactional
	public boolean regist(User user);

	public User login(User user);

	public boolean hasUser(String username);


}
