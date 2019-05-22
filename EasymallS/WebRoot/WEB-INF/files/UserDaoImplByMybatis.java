package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository("userDao")
public class UserDaoImplByMybatis implements UserDao {

	private static SqlSession session;

	static {
		try {
			InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");

			SqlSessionFactory factory = new SqlSessionFactoryBuilder()
					.build(in);

			session = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean queryUser(String username) {
		User user = session.selectOne("domain.UserMapper.queryByUsername",
				username);
		if (user != null)
			return true;
		return false;
	}

	@Override
	public boolean addUser(User user) {
		int count = session.insert("domain.UserMapper.insertUser", user);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public User login(String username, String password) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		User user = session.selectOne("domain.UserMapper.query", map);
		return user;
	}

}
