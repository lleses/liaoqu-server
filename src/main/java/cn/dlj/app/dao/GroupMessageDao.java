package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.GroupMessage;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface GroupMessageDao {

	void add(GroupMessage groupMessage);
	
	void update(@Param("id") Integer id, @Param("status") Integer status);

	List<GroupMessage> findByGroupIdAndUserId(@Param("groupId") Integer groupId, @Param("userId") Integer userId);
}