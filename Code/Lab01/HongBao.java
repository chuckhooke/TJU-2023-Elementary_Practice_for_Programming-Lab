package com.huawei.classroom.student.h51;

import java.math.BigDecimal;

public class HongBao {

	/**
	 * 
	 * @param total  红包总金额，以元为单位，精确到分，系统测试的时候保证总金额至少够每人分得1分钱
	 * @param personCount 分红包的总人数>0
	 * @return 每个人分得的钱数
	 * 规则遵循微信分红包规则 例如：
	 * 要求 每人分得的钱数总和=total
	 * 每个人分得钱数必须是正数，且不能少于1分
	 * 
	 */
	public double[] getHongbao(double total,int personCount) {
		double result[]=new double[personCount];
		double rest = total - 0.01;
		for(int i = 0; i < personCount - 1; i++) {
			double ran = Math.random();
			double assign = ran * (rest - 0.01);
			assign = roundoftwo(assign);
			if(assign < 0.01) assign = 0.01;
			rest -= assign;
			result[i] = assign;
		}
		result[personCount - 1] = roundoftwo(rest + 0.01);
		return result;
		
		
	}
	// 取两位小数
	 public double roundoftwo(double f) {
         BigDecimal bg = new BigDecimal(f);
         double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
         return f1;
     }
}
