package cn.misaka.store.mapper;

import java.util.List;

import cn.misaka.store.entity.Area;

public interface AreaMapper {
	
	/**
	 * 通过市的Code获取数据表中该市下属区的数据
	 * @param CityCode 市的编号
	 * @return 返回该市编号下面所有区对象的集合
	 */
	List<Area> getAreaListByCityCode(String cityCode);
	
	/**
	 * 通过编号查找区的数据
	 * @return 返回区的数据，如果找不到则返回null
	 */
	Area getAreaByCode(String areaCode);
}
