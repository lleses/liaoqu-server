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

	User getByUsername(@Param("username") String username);

	List<User> getByUsernameOrPhone(@Param("content") String content, @Param("userId") Integer userId);

	List<User> getFriends(@Param("userId") Integer userId);
}