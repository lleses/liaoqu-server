package cn.dlj.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.dlj.app.entity.Dynamic;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface DynamicDao {

	void add(Dynamic user);

	void updateCommentNum(@Param("id") Integer id);

	void updatePraiseNum(@Param("id") Integer id);

	List<Dynamic> getByUserId(@Param("userId") Integer userId);

	Dynamic getByIdAndUserId(@Param("id") Integer id,@Param("userId") Integer userId);
}