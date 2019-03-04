package cn.misaka.store.mapper;

import cn.misaka.store.entity.Address;

public interface AddressMapper {
	
	/**
	 * 保存地址数据到数据表中
	 * @param address 用户的地址数据对象
	 * @return 受到影响的行数
	 */
	Integer insert(Address address);
}
