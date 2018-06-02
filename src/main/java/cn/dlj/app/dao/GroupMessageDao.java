package cn.dlj.app.dao;

import cn.dlj.app.entity.GroupMessage;
import cn.dlj.utils.MyBatisDao;

@MyBatisDao
public interface GroupMessageDao {

	void add(GroupMessage groupMessage);
}