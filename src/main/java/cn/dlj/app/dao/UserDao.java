package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.User;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface UserDao {

	void add(User user);

	void add2(User user);

	void update(User user);

	void updateLock(@Param("id") Integer id, @Param("lockPwd") String lockPwd);

	User getByUsername(@Param("username") String username);

	User getById(@Param("id") Integer id);

	List<User> getByUsernameOrPhone(@Param("content") String content, @Param("userId") Integer userId);

	List<User> getFriends(@Param("userId") Integer userId);
}