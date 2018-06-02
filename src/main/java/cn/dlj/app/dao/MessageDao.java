package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Message;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface MessageDao {

	void add(Message message);

	void update(@Param("id") Integer id, @Param("status") Integer status);

	List<Message> findByUserIdAndFriendIdAndStatus(@Param("userId") Integer userId, @Param("friendId") Integer friendId, @Param("status") Integer status);

}