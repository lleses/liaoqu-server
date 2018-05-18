package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.FriendRequests;
import cn.dlj.app.entity.UserFriendRelation;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface FriendRequestsDao {

	void add(FriendRequests friendRequests);

	void addUserFriend(UserFriendRelation userFriendRelation);

	void update(@Param("id") Integer id, @Param("status") Integer status);

	FriendRequests getByUserIdAndFriendId(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

	List<FriendRequests> getByUserId(@Param("userId") Integer userId);

	UserFriendRelation getUserFriendRelation(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

}