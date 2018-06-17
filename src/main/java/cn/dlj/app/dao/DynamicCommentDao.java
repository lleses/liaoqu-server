package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.DynamicComment;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface DynamicCommentDao {

	void add(DynamicComment dynamicComment);

	List<DynamicComment> getByDynamicId(@Param("dynamicId") Integer dynamicId);
}