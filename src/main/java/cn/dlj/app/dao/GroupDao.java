package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Group;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface GroupDao {

	void add(Group group);

	Group findById(@Param("id") Integer id);

	List<Group> findByUserId(@Param("userId") Integer userId);


}