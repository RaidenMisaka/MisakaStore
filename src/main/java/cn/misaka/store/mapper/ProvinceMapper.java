package cn.misaka.store.mapper;

import java.util.List;

import cn.misaka.store.entity.Province;

public interface ProvinceMapper {
	
	/**
	 * 获取数据表中省的数据
	 * @return 返回所有省对象的集合
	 */
	List<Province> getProvinceList();
	
	/**
	 * 通过编号查找省的数据
	 * @return 返回省的数据，如果找不到则返回null
	 */
	Province getProvinceByCode(String provinceCode);
}
