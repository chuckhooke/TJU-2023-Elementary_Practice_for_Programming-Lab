/**
 * 
 */
package com.huawei.classroom.student.h52;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 质因数分解
 * @author Administrator
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumDecompose   home=new NumDecompose  ();
		Set<Integer> target=new HashSet<Integer>();
		target.add(2);
//		target.add(5);
//		target.add(7);
		//此题目结果要求精确匹配答案
		Set<Integer> result=home.decompose(2);
		Iterator it = result.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		if(target.equals(result)) {
			System.out.println("pass");
		}
	}

}
