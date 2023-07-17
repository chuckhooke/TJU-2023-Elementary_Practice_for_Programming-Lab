package com.huawei.classroom.student.h59;

import java.util.HashSet;
import java.util.Set;

public class Test {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//本文件中存放了若干的化学反应方程式（总数量不会超过1000个）
//		#某个文本本文件中存放了一系列的化学反应 #表示注释
//		# 化学反应以 = 分为了左侧和右侧；不同化合物之间至少有 一个空格
//		# A + B = C + D 意味着体系中如果有了 A B 就可以生成C D，同样如果有C D 也可以生成 A B 
//		# 所有反应 反应物前系数均为 1 
//		根据一个体系中初始化合物 ，最后可能都存在什么化合物
		String reactionFilename="C:\\Users\\hkhk3\\Desktop\\Java\\project\\assignment\\src\\com\\huawei\\classroom\\student\\h59\\reactions.txt";
		ReactionTools tool=new ReactionTools();
		Set<String> init=new HashSet<String>();
		//初始物种 只有 HCL  NaOH
		init.add("HCl");
		init.add("NaOH");
		Set<String> result=tool.findAllComponents(reactionFilename, init);
		for(String re:result) {
			System.out.println(re);
		}
		Set<String> target=new HashSet<String>();
		//根据reacitons.txt判断体系可以衍生出如下物质
			target.add("HCl");
		target.add("NaOH");
		target.add("H+");
		target.add("Cl-");
		target.add("Na+");
		target.add("OH-");
		target.add("H2O");
		target.add("NaCl");
		if(target.equals(result)) {
			System.out.println("pass!");
		}
	}


}
