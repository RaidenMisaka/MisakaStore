package cn.misaka.store.service;

import cn.misaka.store.entity.User;
import cn.misaka.store.service.ex.PasswordNotMatchException;
import cn.misaka.store.service.ex.UserNotFoundException;
import cn.misaka.store.service.ex.UsernameConflictException;

public interface IUserService {
	/**
	 * 注册方法，首先判断提交的用户名是否被占用，被占用则注册不成功，使用异常机制
	 * 提示用户，未被占用则注册成功。返回该User对象。
	 * @param user 用户提交的User对象相关的数据，不包含id
	 * @return 注册成功，则返回包含id的User对象。失败则使用异常处理，无返回
	 * @throws UsernameConflicException 用户名已经存在
	 */
	User reg(User user);
	
	/**
	 * 通过id查找用户 
	 * @param id 给定的id
	 * @return 如果找到则返回User 找不到则抛出异常
	 * @throws UserNotFoundException 找不到用户异常
	 */
	User findUserById(Integer id);

	/**
	 * 通过用户名查找用户数据
	 * @param username 用户名
	 * @return 找到则返回该User对象，找不到则返回null
	 */
	User findUserByUsername(String username);

	/**
	 * 通过用户名和密码登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 登录成功返回该User对象
	 * @throws UserNotFoundException 找不到该用户异常
	 * @throws PasswordNotMatchException 密码不匹配异常
	 */
	User login(String username,String password);
	
	/**
	 * 修改密码
	 * @param uid 需要修改的用户的id
	 * @param oldPassword 用户的未加密的原密码
	 * @param newPassword 用户的未加密的新密码
	 * @return 受到影响的行数，修改成功则返回1，修改失败则返回不是1
	 * @throws UserNotFoundException 找不到该用户异常。
	 * @throws PasswordNotMatchException 密码不正确异常
	 */
	Integer changePassword 
		(Integer uid,String oldPassword,String newPassword) 
				throws UserNotFoundException,PasswordNotMatchException;

	
	/**
	 * 修改用户个人信息
	 * @param uid 要修改的用户的id
	 * @param avatar 新的头像路径，如果不修改，则使用null
	 * @param username 新的用户名
	 * @param gender 新的性别
	 * @param phone 新的电话号码
	 * @param email 新的电子邮箱
	 * @return 受影响的行数
	 * @throws UserNotFoundException 抛出没有找到该用户的异常
	 * @throws UsernameConflictException 抛出用户名冲突的异常
	 */
	Integer changeInfo(
			Integer uid,String avatar,String username,Integer gender,String phone,String email) throws UserNotFoundException,UsernameConflictException;
	
	/**
	 * 获取加密后的密码
	 * @param password 原始密码
	 * @param salt 盐
	 * @return 加密后的密码
	 */
	String getMd5Password(String password,String salt);
}
