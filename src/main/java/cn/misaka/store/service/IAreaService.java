package cn.misaka.store.service;

import java.util.List;

import cn.misaka.store.entity.Area;

public interface IAreaService {
	
	/**
	 * 通过市级编码获取区的数据对象集合
	 * @param cityCode 市级Code编码
	 * @return 返回对应区的对象集合
	 */
	List<Area> getAreaListByCityCode(String cityCode);
	
	/**
	 * 通过区的编码查询该区对象数据
	 * @param areaCode 区的Code编码
	 * @return 返回区的对象数据
	 */
	Area getAreaByCode(String areaCode);
}
