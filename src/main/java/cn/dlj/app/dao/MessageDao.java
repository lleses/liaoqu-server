package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Message;
import cn.dlj.app.entity.MessageList;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface MessageDao {

	void add(Message message);

	void addList(MessageList messageList);

	void update(@Param("id") Integer id, @Param("status") Integer status);

	void updateList(MessageList messageList);

	List<Message> getMsg(@Param("userId") Integer userId, @Param("friendId") Integer friendId, @Param("status") Integer status);

	MessageList getMsgList(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

	List<MessageList> getMsgListByUserId(@Param("userId") Integer userId);

	void delMsgList(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

}