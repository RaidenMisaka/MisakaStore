package cn.misaka.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.misaka.store.entity.Address;
import cn.misaka.store.entity.Province;
import cn.misaka.store.service.IAddressService;
import cn.misaka.store.service.IProvinceService;
import cn.misaka.store.service.ex.InsertDataException;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController {
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private IProvinceService provinceService;

	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap) {
		List<Province> provinces = provinceService.getProvinceList();
		// 封装所有的省的列表,并转发
		modelMap.addAttribute("provinces", provinces);
		// 执行转发
		return "address_list";
	}

	@RequestMapping(value = "/handle_add.do", method = RequestMethod.POST)
	public String handleAdd(Address address, HttpSession session, ModelMap modelMap) {
		// 忽略：检查数据的有效性
		// 从session中获取uid，username
		Integer uid = getUidFromSession(session);
		String username = session.getAttribute("username").toString();
		// 将uid封装到address中
		address.setUid(uid);
		try {
			// 调用业务层对象的add(username,address)方法
			addressService.add(username, address);
			// 增加成功，则重定向
			// 当前位置： /address/handle_add.do
			// 目标位置： /address/list.do
			return "redirect:list.do";
		} catch (InsertDataException e) {
			// 封装错误信息
			modelMap.addAttribute("message", e.getMessage());
			// 转发到提示错误的页面
			return "error";
		}
	}
}
