package cn.misaka.store.entity;

import java.io.Serializable;

public class Province implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5933703571425725458L;
	private Integer id;
	private String provinceCode;
	private String provinceName;

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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Province(Integer id, String provinceCode, String provinceName) {
		super();
		setId(id);
		setProvinceCode(provinceCode);
		setProvinceName(provinceName);
	}

	@Override
	public String toString() {
		return "Province [id=" + id + ", provinceCode=" + provinceCode + ", provinceName=" + provinceName + "]";
	}

	public Province() {
		super();
	}

}
