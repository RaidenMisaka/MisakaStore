package cn.misaka.store.service;

import cn.misaka.store.entity.Address;
import cn.misaka.store.service.ex.InsertDataException;

public interface IAddressService {

	/**
	 * 增加新的收货地址
	 * @param username 当前登录的用户名
	 * @param address 收货地址数据
	 * @return 成功增加的收货地址数据，且包含数据的id
	 * @throws InsertDataException 抛出插入数据错误的异常
	 */
	Address add(String username,Address address) throws InsertDataException;
}
