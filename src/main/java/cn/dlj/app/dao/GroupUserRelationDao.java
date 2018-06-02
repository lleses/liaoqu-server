package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.GroupUserRelation;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface GroupUserRelationDao {

	void add(GroupUserRelation groupUserRelation);

	GroupUserRelation findByUserIdAndGroupId(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

	List<GroupUserRelation> findByGroupId(@Param("groupId") Integer groupId);

}