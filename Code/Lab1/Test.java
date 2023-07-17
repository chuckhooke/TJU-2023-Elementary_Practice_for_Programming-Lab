package com.huawei.classroom.student.h51;

/**
 * 分红包 问题
 * 
 * 在本包下增加合适的类和方法，使得Test类能够测试通过
 * 
 * 不要引用jdk1.8以外第三方的包
 * 
 * @author cjy
 *
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 完成分红包金额的程序 ，调用方式如下：
		HongBao home = new HongBao();
		//返回每个人应得的的钱数，分红包规则参考微信的随机红包
		//此题目结果不要求精确匹配
		double total = 0.06;
		int personcnt = 3;
		double sum = 0;
		double result[] = home.getHongbao(total, personcnt);
		for(int i = 0; i < personcnt; i++) {
			sum += result[i];
			System.out.println(result[i]);
		}
		if(sum == total) System.out.println("case ok");
	}

}
