package cn.dlj.app.dao;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.FriendRequests;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface FriendRequestsDao {

	void add(FriendRequests friendRequests);

	FriendRequests getByUserIdAndFriendId(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

}