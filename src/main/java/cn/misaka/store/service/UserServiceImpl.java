package cn.misaka.store.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.misaka.store.entity.User;
import cn.misaka.store.mapper.UserMapper;
import cn.misaka.store.service.ex.PasswordNotMatchException;
import cn.misaka.store.service.ex.UserNotFoundException;
import cn.misaka.store.service.ex.UsernameConflictException;
import cn.misaka.store.util.Validator;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	public User reg(User user) {
		User result = findUserByUsername(user.getUsername());
		if(result == null) {
			//用户名不存在
			String uuid = UUID.randomUUID().toString();
			String md5Password = getMd5Password(user.getPassword(),uuid);
			user.setPassword(md5Password);
			user.setUuid(uuid);
			
			Date now = new Date();
			user.setCreatedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
			
			userMapper.insert(user);
			return userMapper.findUserByUsername(user.getUsername());
		} else {
			//已经存在
			throw new UsernameConflictException("用户名已经存在");
		}
	}

	public User findUserByUsername(String username) {
		return userMapper.findUserByUsername(username);
	}

	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

	public Integer changePassword(Integer uid, String oldPassword, String newPassword) {
		// 根据uid获取用户数据
		User user = findUserById(uid);
		// 判断是否获取到数据，如果没有，则抛出异常：UserNotFoundException
		if(user == null ) {
			throw new UserNotFoundException("找不到该用户");
		} else {
			// 从用户数据中获取uuid
			String uuid = user.getUuid();
			// 调用getMd5Password(OldPassword,uuid)用户提供的原密码进行加密
			String md5Password = getMd5Password(oldPassword, uuid);
			// 判断加密后的原密码，与用户数据中的密码，是否正确
			if (md5Password.equals(user.getPassword())) {
				// -- 原密码正确
				// -- 调用getMd5Password(newPassword,uuid)将用户提供的新密码进行加密
				String md5NewPassword = getMd5Password(newPassword, uuid);
				// -- 执行修改：
				// -- 调用持久层对象的Integer changePassword(Integer uid,String newMd5Password);并获取返回值
				Integer result = userMapper.changePassword(uid, md5NewPassword,user.getUsername(),new Date());
				// -- 判断返回值是否为1
				if(result == 1) {
					// -- -- 值为1：操作正确，返回1
					return 1;
				} else {
					// -- -- 值不为1：操作失败，抛出异常：UserNotFoundException
					throw new UserNotFoundException("找不到该用户");
				}
			// -- 原密码错误
			} else {
				// -- 抛出异常：PasswordNotMatchException
				throw new PasswordNotMatchException("密码不正确");
			}
		}
	}

	public Integer changeInfo(Integer uid, String avatar,String username, Integer gender, String phone, String email)
			throws UserNotFoundException {
		User data = findUserById(uid);
		if(data != null) {
			// 验证数据的有效性
			// -- 如果用户名格式错误，视为null
			username = Validator.checkUsername(username) ? username : null;  
			// -- 如果电话号码格式错误，视为null
			phone = Validator.checkPhone(phone) ? phone : null;
			// -- 如果电子邮箱格式错误，视为null
			email = Validator.checkEmail(email) ? email : null;
			
			// 如果修改用户名,则用户名不可是其他用户已经注册过的
			// 判断用户名是否为null仅当不为null时执行此项
			if (username != null) {
				// 调用自身的findUserByUsername()根据用户名查询
				User result = findUserByUsername(username);		
				if (result == null) {
					// -- 结果为null：用户名没被占用，可以使用
				} else {
					if(result.getId()==uid) {
						// -- -- 如果id匹配，用户名是自己的：设置为null
						username = null;
					} else {
						// -- -- 如果id不匹配，则用户名是别人的：抛出异常UsernameConflictException
						throw new UsernameConflictException("用户名"+result.getUsername()+"已经被注册");
					}
				}
			}
			// 将各参数都封装到User类型的对象中
			User user = new User(uid, username, gender, phone, email, avatar);
			
			// 封装日志信息
			user.setModifiedUser(data.getUsername());
			user.setModifiedTime(new Date());
			// 调用持久层对象的changeInfo(User)方法,并获取返回值
			Integer result = userMapper.changeInfo(user);
			// 判断返回值是否为：1
			if(result == 1) {
				// -- 返回1
				return 1;
			} else {
				// -- 如果不为1：抛出异常UserNotFoundException
				throw new UserNotFoundException("需要修改的用户不存在");
			}
		} else {
			throw new UserNotFoundException("需要修改的用户不存在");
		}
	}

	public User login(String username, String password) {
		User user = userMapper.findUserByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("用户名不存在");
		} else {
			String uuid = user.getUuid();
			String pwd = getMd5Password(password,uuid);
			if(user.getPassword().equals(pwd)) {
				//出于安全，可以把下面两个属性设置为null
				user.setUuid(null);
				user.setPassword(null);
				return user;
			} else {
				throw new PasswordNotMatchException("密码不匹配");
			}
		}
	}

	public String getMd5Password(String password, String salt) {
		// ----------------------------------------
		// --------以下加密规则是自行设计的----------
		// ----------------------------------------
		// 先把原密码加密为MD5密码并且转大写
		String str1 = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
		// 然后把结果和盐值拼接
		String str2 = str1+salt.toUpperCase();
		// 再次加密得到最终结果
		String str3 = DigestUtils.md5DigestAsHex((str2+salt).getBytes()).toUpperCase();
		return str3;
	} 
}
