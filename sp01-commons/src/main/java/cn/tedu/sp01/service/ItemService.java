package cn.tedu.sp01.service;

import java.util.List;

import cn.tedu.sp01.pojo.Item;

public interface ItemService {
	//根据订单的id,获取订单中的商品列表
	List<Item> getItems(String orderId);
	
	//用户保存订单时,购买的所有商品,要减少商品库存
	void decreaseNumbers(List<Item> items);
}
