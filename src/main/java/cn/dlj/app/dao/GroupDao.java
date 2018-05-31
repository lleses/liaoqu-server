package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Group;
import cn.dlj.app.entity.GroupUserRelation;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface GroupDao {

	void add(Group group);

	void addGroupUser(GroupUserRelation groupUserRelation);

	List<Group> getGroupList(@Param("userId") Integer userId);

}