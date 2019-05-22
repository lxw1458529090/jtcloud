package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate = null;

	@Override
	public boolean queryUser(String username) {
		String sql = "select * from user where username = ?";
		SqlRowSet set = jdbcTemplate.queryForRowSet(sql, username);
		if (set.next())
			return true;
		return false;
	}

	@Override
	public boolean addUser(User user) {
		String sql = "insert into user values(null,?,?,?,?)";
		int update = jdbcTemplate.update(sql, user.getUsername(),
				user.getPassword(), user.getNickname(), user.getEmail());
		if (update > 0)
			return true;
		return false;
	}

	@Override
	public User login(String username, String password) {
		String sql = "select * from user where username=? and password=?";
		return jdbcTemplate
				.queryForObject(sql,
						new BeanPropertyRowMapper<User>(User.class), username,
						password);
	}

}
