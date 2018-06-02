package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.MessageList;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface MessageListDao {

	void add(MessageList messageList);

	void update(MessageList messageList);

	void updateByUserIdAndGroupId(MessageList messageList);

	void del(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

	MessageList findByUserIdAndFriendId(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

	MessageList findByUserIdAndGroupId(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

	List<MessageList> findByUserId(@Param("userId") Integer userId);

}