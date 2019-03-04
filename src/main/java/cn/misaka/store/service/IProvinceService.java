package cn.misaka.store.service;

import java.util.List;

import cn.misaka.store.entity.Province;

public interface IProvinceService {
	
	/**
	 * 查询省数据
	 * @return 返回省的数据集合
	 */
	List<Province> getProvinceList();
	
	
	/**
	 * 通过省的code查询省数据
	 * @param provinceCode 省的Code编码
	 * @return 对应的Province对象
	 */
	Province getProvinceByCode(String provinceCode);
}
