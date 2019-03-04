package cn.misaka.store.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.misaka.store.entity.Address;
import cn.misaka.store.mapper.AddressMapper;
import cn.misaka.store.service.ex.InsertDataException;

@Service("addressService")
public class AddressServiceImpl implements IAddressService{
	
	@Autowired 
	private AddressMapper addressMapper;

	public Address add(String username, Address address) {
		// 判断address中的uid和recvName是否为NULL
		// 封装日志信息
		Date now = new Date();
		address.setCreatedUser(username);
		address.setModifiedUser(username);
		address.setCreatedTime(now);
		address.setModifiedTime(now);
		// 执行增加
		Integer rows = addressMapper.insert(address);
		if(rows == 1) {
			// 返回
			return address;
		} else {
			// 增加出错，抛出异常
			throw new InsertDataException("增加收货地址信息失败！"+address);
		}
	}
	
	
}
