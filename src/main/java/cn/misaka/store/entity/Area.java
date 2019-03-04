package cn.misaka.store.entity;

import java.io.Serializable;

public class Area implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1177782451547703689L;

	private Integer id;
	private String cityCode;
	private String areaCode;
	private String areaName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Area() {
		super();
	}

	public Area(Integer id, String cityCode, String areaCode, String areaName) {
		super();
		setAreaCode(areaCode);
		setAreaName(areaName);
		setCityCode(cityCode);
		setId(id);
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", cityCode=" + cityCode + ", areaCode=" + areaCode + ", areaName=" + areaName + "]";
	}

}
