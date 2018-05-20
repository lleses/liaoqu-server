package cn.dlj.app.service;

import java.util.ArrayList;
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
		dao.add2(user);
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

	public List<User> getByUsernameOrPhone(String content, Integer userId) {
		if (content != null && userId != null) {
			List<User> user = dao.getByUsernameOrPhone(content, userId);
			return user;
		}
		return null;
	}

	public List<User> getFriends(Integer userId) {
		List<User> user = new ArrayList<User>();
		if (userId != null) {
			user = dao.getFriends(userId);
		}
		return user;
	}
}