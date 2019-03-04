package cn.misaka.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.misaka.store.entity.Province;
import cn.misaka.store.mapper.ProvinceMapper;

@Service("provinceService")
public class ProvinceServiceImpl implements IProvinceService {

	@Autowired
	private ProvinceMapper provinceMapper;

	public List<Province> getProvinceList() {
			return provinceMapper.getProvinceList();
	}

	public Province getProvinceByCode(String provinceCode) {
		return provinceMapper.getProvinceByCode(provinceCode);
	}

}
