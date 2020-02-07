package cn.tedu.sp01.service;

import cn.tedu.sp01.pojo.User;

public interface UserService {
	//根据用户id,获取用户的信息
	User getUser(Integer id);
	
	//用户保存一个订单时,增加用户的积分
	void addScore(Integer id, Integer score);
}
