package cn.misaka.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.misaka.store.entity.User;

public interface UserMapper {
	
	/**
	 * 插入一条用户数据
	 * @param user 用户对象
	 * @return 受到影响的行数
	 */
	Integer insert(User user);
	
	/**
	 * 通过用户名查找用户数据
	 * @param username 用户名
	 * @return 返回用户对象，如果没找到则返回null
	 */
	User findUserByUsername(String username);
	
	/**
	 * 通过id查找用户数据
	 * @param id 用户的id
	 * @return 如果找到则返回该用户，找不到则返回null;
	 */
	User findUserById(Integer id);
	
	/**
	 * 修改密码
	 * @param uid 用户的id
	 * @param password 新的密码
	 * @return 返回受到影响的行数，如果为1则成功，返回0则修改失败
	 */
	Integer changePassword(
			@Param("uid") Integer uid,
			@Param("password") String password,
			@Param("modifiedUser") String modifiedUser,
			@Param("modifiedTime") Date modifiedTime);
	/**
	 * 修改用户信息
	 * @param user 封装了被修改的用户id，新用户名，新性别，新手机号，新电子邮箱
	 * @return 返回受到影响的行数
	 */
	Integer changeInfo(User user);
}
