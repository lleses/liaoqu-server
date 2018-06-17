package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.DynamicPraise;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface DynamicPraiseDao {

	void add(DynamicPraise dynamicPraise);

	void del(@Param("dynamicId") Integer dynamicId, @Param("userId") Integer userId);

	List<DynamicPraise> getByDynamicId(@Param("dynamicId") Integer dynamicId);

}