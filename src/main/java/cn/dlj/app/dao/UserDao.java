package cn.dlj.app.dao;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.User;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface UserDao {

	void add(User user);

	void update(User user);

	User getByUsername(@Param("username") String username);
}