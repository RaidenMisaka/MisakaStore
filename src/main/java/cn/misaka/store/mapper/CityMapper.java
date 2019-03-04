package cn.misaka.store.mapper;

import java.util.List;

import cn.misaka.store.entity.City;

public interface CityMapper {
	
	/**
	 * 获取数据表中市的数据
	 * @param ProvinceCode 省的编号
	 * @return 返回该省编号下面所有市对象的集合
	 */
	List<City> getCityListByProvinceCode(String provinceCode);
	
	/**
	 * 通过编号查找市的数据
	 * @return 返回市的数据，如果找不到则返回null
	 */
	City getCityByCode(String cityCode);
}
