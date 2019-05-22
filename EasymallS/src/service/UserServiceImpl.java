package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import util.MD5Util;

import dao.UserDao;
import domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao = null;

	@Override
	public boolean regist(User user) {
		boolean flag = userDao.queryUser(user.getUsername())!=null;

		if (flag)
			return false;
		else {
			user.setPassword(MD5Util.md5(user.getPassword()));
			flag = userDao.addUser(user);
			// int i = 1/0;
			return flag;
		}
	}

	@Override
	public User login(User user) {
		System.out.println("!!!!!" + userDao.getClass());
		return userDao.login(user);
	}

	@Override
	public boolean hasUser(String username) {
		return userDao.queryUser(username)!=null;
	}

}
