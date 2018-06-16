package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Dynamic;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface DynamicDao {

	void add(Dynamic user);
	
	List<Dynamic> getByUserId(@Param("userId") Integer userId);
}