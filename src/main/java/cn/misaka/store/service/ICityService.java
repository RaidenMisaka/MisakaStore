package cn.misaka.store.service;

import java.util.List;

import cn.misaka.store.entity.City;

public interface ICityService {

	/**
	 * 通过省级编码查询下属市的数据集合
	 * @param provinceCode 省级编码
	 * @return 返回市的数据集合
	 */
	List<City> getCityListByProvinceCode(String provinceCode);
	
	/**
	 *  通过市的编码查询该市的数据
	 * @param cityCode 市的Code编码
	 * @return 返回该市的City对象
	 */
	City getCityByCode(String cityCode);
}
