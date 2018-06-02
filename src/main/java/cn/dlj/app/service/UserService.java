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
		if (user != null && user.getId() != null) {
			Integer id = user.getId();
			dao.add2(user);
			return id;
		}
		return null;
	}

	@Transactional
	public void update(User user) {
		if (user.getId() != null && user.getUsername() != null && user.getPwd() != null) {
			dao.update(user);
		}
	}

	@Transactional
	public void updateLock(Integer id, String lockPwd) {
		if (id != null && lockPwd != null) {
			dao.updateLock(id, lockPwd);
		}
	}

	public User getByUsername(String username) {
		if (username != null) {
			User user = dao.getByUsername(username);
			return user;
		}
		return null;
	}

	public User getById(Integer id) {
		if (id != null) {
			User user = dao.getById(id);
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
	
	//TODO
	@Transactional
	public void del() {
		dao.del();
		dao.del2();
		dao.del3();
		dao.del4();
		dao.del5();
		dao.del6();
		dao.del7();
		dao.del8();
	}
}