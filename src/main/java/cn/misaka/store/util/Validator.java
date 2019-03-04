package cn.misaka.store.util;
public final class Validator {
	// 驗證用戶名的正則表達式
	public static final  String REGEX_USERNAME ="^[a-zA-Z]{1}[a-zA-Z0-9_]{5,15}$";
	
	// 驗證密碼的正則表達式
	public static final  String REGEX_PASSWORD ="^[A-Za-z][A-za-z1-9_-]+$";
	
	// 驗證電話號碼的正則表達式
	public static final String REGEX_PHONE="^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	
	// 驗證電子郵箱的正則表達式
	public static final String REGEX_EMAIL="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	private Validator() {
		super();
	}
	
	/**
	 * 驗證用戶名
	 * @param username 需要被驗證格式的用戶名
	 * @return 如果符合格式要求則返回true，否則返回false
	 */
	public static boolean checkUsername(String username) {
		boolean result = username.matches(REGEX_USERNAME);
		return result;
	}
	
	/**
	 * 驗證密碼
	 * @param password 需要被驗證格式的密碼
	 * @return 如果符合格式要求則返回true，否則返回false
	 */
	public static boolean checkPassword(String password) {
		boolean result = password.matches(REGEX_PASSWORD);
		return result;
	}
	
	/**
	 * 驗證電話號碼
	 * @param phone 需要被驗證格式的電話號碼
	 * @return 如果符合格式要求則返回true，否則返回false
	 */
	public static boolean checkPhone(String phone) {
		boolean result = phone.matches(REGEX_PHONE);
		return result;
	}
	
	
	/**
	 * 驗證電子郵箱
	 * @param email 需要被驗證格式的電子郵箱
	 * @return 如果符合格式要求則返回true，否則返回false
	 */
	public static boolean checkEmail(String email) {
		boolean result = email.matches(REGEX_EMAIL);
		return result;
	}
}
