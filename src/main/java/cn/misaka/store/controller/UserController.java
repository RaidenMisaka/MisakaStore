package cn.misaka.store.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.misaka.store.entity.ResponseResult;
import cn.misaka.store.entity.User;
import cn.misaka.store.service.IUserService;
import cn.misaka.store.service.ex.PasswordNotMatchException;
import cn.misaka.store.service.ex.ServiceException;
import cn.misaka.store.service.ex.UserNotFoundException;
import cn.misaka.store.service.ex.UsernameConflictException;
import cn.misaka.store.util.Validator;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;

	/**
	 * 上传的头像的最大尺寸，单位：kb
	 */
	public static final long MAX_AVATAR_SIZE = 50;

	@RequestMapping("/reg.do")
	public String showReg() {
		return "user_reg";
	}

	@RequestMapping("/login.do")
	public String showLogin() {
		return "user_login";
	}

	@RequestMapping("/change_password.do")
	public String showChangePassword() {
		return "user_change_password";
	}

	@RequestMapping("/user_change_info.do")
	public String showChangeInfo(HttpSession session, ModelMap modelMap) {
		// 获取当前登录的用户的id
		Integer uid = getUidFromSession(session);
		// 查询当前登录的用户的数据
		User user = userService.findUserById(uid);
		// 将用户数据封装，以准备转发
		modelMap.addAttribute("user", user);
		return "user_change_info";
	}

	@RequestMapping(value = "/handle_change_info.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> handleChangeInfo(MultipartHttpServletRequest request, MultipartFile avatar,
			String username, Integer gender, String phone, String email, HttpSession session) {
		ResponseResult<String> rr;
		// 用户上传的头像在服务器的路径
		String avatarPath = null;
		// 从session中获取uid
		Integer uid = getUidFromSession(session);
		// =============上传头像=============
		// 判断用户是否选择上传新的头像
		if (!avatar.isEmpty()) {
			// 验证文件类型
			String contentType = avatar.getContentType();
			if (!"image/png".equals(contentType) && !"image/jpeg".equals(contentType)
					&& !"image/bmp".equals(contentType)) {
				// 错误
				rr = new ResponseResult<String>();
				rr.setState(ResponseResult.STATE_ERR);
				rr.setMessage("不支持上传" + contentType + "类型的文件！");
				return rr;
			}

			// 验证文件的大小
			long size = avatar.getSize();
			if (size > MAX_AVATAR_SIZE * 1024) {
				rr = new ResponseResult<String>(ResponseResult.STATE_ERR, "上传的头像文件不允许超过30kb");
			}

			// 保存所有用户头像的文件夹
			String avatarDirPath = request.getServletContext().getRealPath("/upload/image");
			File avatarDir = new File(avatarDirPath);
			System.out.println("测试avatarDirPath：" + avatarDirPath);
			// 如果保存头像的文件夹不存在，则创建：
			// if(!avatarDir.exists()) {
			// avatarDir.mkdirs();
			// }

			// 获取客户端上传的原始的文件的文件名
			String originalFilename = avatar.getOriginalFilename();
			// 获取客户端上传的原始的文件的扩展名
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

			// 头像文件的文件名，每个用户的头像文件名都应该不相同
			Date date = new Date();
			String filename = getDateString(date) + uid + suffix;
			// 目标文件，即在服务端保存的用户头像文件
			File dest = new File(avatarDir, filename);
			try {
				// 将用户上传的头像数据保存到文件
				avatar.transferTo(dest);
				// 确定用户头像在服务器端的路径
				avatarPath = "upload/image/" + filename;
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		// ==========执行修改个人资料==========
		try {
			userService.changeInfo(uid, avatarPath, username, gender, phone, email);
			rr = new ResponseResult<String>(ResponseResult.STATE_OK);
			
			// 将头像路径封装在返回对象的data属性中
			System.out.println("avatarPath:" + avatarPath);
			rr.setData(avatarPath);
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<String>(-1, e.getMessage());
		} catch (UsernameConflictException e) {
			rr = new ResponseResult<String>(-3, e.getMessage());
		}
		return rr;
	}

	@RequestMapping(value = "/handle_change_password.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> handleChangePassword(@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword, HttpSession session) {
		// 声明返回值
		ResponseResult<String> rr;
		try {
			// -- 通过session获取uid
			Integer uid = getUidFromSession(session);
			// -- 调用业务层对象的changePassword(Integer uid,oldPassword,newPassword)
			userService.changePassword(uid, oldPassword, newPassword);
			// -- 创建rr对象，表示成功
			rr = new ResponseResult<String>(ResponseResult.STATE_OK);
		} catch (UserNotFoundException e) {
			// -- 创建rr对象，-1,e.getMessage()
			rr = new ResponseResult<String>(-1, e.getMessage());
		} catch (PasswordNotMatchException e) {
			// -- 创建rr对象，-2,e.getMessage()
			rr = new ResponseResult<String>(-2, e.getMessage());
		}
		return rr;
	}

	@RequestMapping(value = "/handle_reg.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> handleReg(@RequestParam("username") String username,
			@RequestParam("password") String password, String email, Integer gender, String phone) {
		// 声明返回值
		ResponseResult<String> rr;
		// 验证数据的有效性，即基本格式
		if (!Validator.checkUsername(username)) {
			rr = new ResponseResult<String>(ResponseResult.STATE_ERR, "您输入的用户名有误！");
			return rr;
		}
		if (!Validator.checkPassword(password)) {
			rr = new ResponseResult<String>(ResponseResult.STATE_ERR, "您输入的密码有误！");
			return rr;
		}
		try {
			// 封装User对象
			User user = new User(username, password, gender, phone, email);
			// 调用业务层的注册方法
			userService.reg(user);
			rr = new ResponseResult<String>(ResponseResult.STATE_OK);
		} catch (ServiceException e) {
			rr = new ResponseResult<String>(e);
		}
		return rr;
	}

	@RequestMapping(value = "/handle_login.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> handleLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		ResponseResult<String> rr;
		try {
			User user = userService.login(username, password);
			session.setAttribute("uid", user.getId());
			session.setAttribute("username", user.getUsername());
			rr = new ResponseResult<String>(ResponseResult.STATE_OK);
		} catch (UserNotFoundException e) {
			rr = new ResponseResult<String>(-1, e.getMessage());
		} catch (PasswordNotMatchException e) {
			rr = new ResponseResult<String>(-2, e.getMessage());
		}
		return rr;
	}

	// 多个线程同时修改同一个数据，才会引发线程安全问题
	private final String pattern = "yyyyMMddHHmmss";
	private final SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);

	private String getDateString(Date date) {
		return sdf.format(date);
	}
}
