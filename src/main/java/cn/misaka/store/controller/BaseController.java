package cn.misaka.store.controller;

import javax.servlet.http.HttpSession;

/**
 * 项目中所有控制器类的基类
 * @author asus-pc
 *
 */
public abstract class BaseController {

	/**
	 * 从session中获取用户的id
	 * @param session HttpSession对象
	 * @return 用户的id
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
}
