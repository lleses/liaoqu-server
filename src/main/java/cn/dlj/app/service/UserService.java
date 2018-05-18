package cn.dlj.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dlj.app.dao.UserDao;
import cn.dlj.app.entity.User;

/**
 * 用户
 */
@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserDao dao;

	@Transactional
	public Integer add(User user) {
		dao.add(user);
		if (user != null && user.getId() != null) {
			return user.getId();
		}
		return null;
	}

	@Transactional
	public void update(User user) {
		if (user.getId() != null && user.getUsername() != null && user.getPwd() != null) {
			dao.update(user);
		}
	}

	public User getByUsername(String username) {
		if (username != null) {
			User user = dao.getByUsername(username);
			return user;
		}
		return null;
	}

	public List<User> getByUsernameOrPhone(String content) {
		if (content != null) {
			List<User> user = dao.getByUsernameOrPhone(content);
			return user;
		}
		return null;
	}
}