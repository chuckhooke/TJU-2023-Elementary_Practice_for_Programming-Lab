/**
 * 
 */
package com.huawei.classroom.student.h54;

/**
 * @author Administrator
 *
 */
public class PasswordChecker {
	/**
	 * 判断一个口令是否是一个复杂度合法的口令，复杂度合法的口令有如下要求：
	 * 1  长度>=8
	 * 2 最少包含一个数字
	 * 3 最少包含一个小写英文字母
	 * 4 虽少包含一个大写英文字母
	 * 5 最少包含一个特殊字符 特殊字符定义为   ~!@#$%^&*()_+
	 * 
	 *   
	 */
	public boolean isValidPassword(String password){
		if(password.length() < 8) return false;
		boolean flagnum = false;
		boolean flagupletter = false;
		boolean flaglowletter = false;
		boolean flagspec = false;
		char[] charr = password.toCharArray();
		for(int i = 0; i < charr.length; i++) {
			if(charr[i] >= '0' && charr[i] <= '9') flagnum = true;
			if(charr[i] >= 'a' && charr[i] <= 'z') flagupletter = true;
			if(charr[i] >= 'A' && charr[i] <= 'Z') flaglowletter = true;
			if(charr[i] >= 'A' && charr[i] <= 'Z') flaglowletter = true;
			String spec = "~!@#$%^&*()_+";
			char[] specarr = spec.toCharArray();
			for(int j = 0; j < specarr.length; j++) {
				if(charr[i] == specarr[j]) flagspec = true;
			}
		}
		
		return flagnum && flagupletter && flaglowletter && flagspec;
		
	}
}
