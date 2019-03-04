package cn.misaka.store.entity;

import java.io.Serializable;

public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3260175688195646546L;
	
	private Integer id;
	private String provinceCode;
	private String cityCode;
	private String cityName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public City() {
		super();
	}

	public City(Integer id, String provinceCode, String cityCode, String cityName) {
		super();
		setCityCode(cityCode);
		setCityName(cityName);
		setId(id);
		setProvinceCode(provinceCode);
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", provinceCode=" + provinceCode + ", cityCode=" + cityCode + ", cityName=" + cityName
				+ "]";
	}

}
