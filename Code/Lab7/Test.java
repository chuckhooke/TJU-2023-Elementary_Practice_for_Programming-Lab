package com.huawei.classroom.student.h57;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//递归统计一个目录下所有文件的大小的加和
		FileTool  tool=new FileTool ();
		//此题目要求精确匹配答案
		long length=tool.recursiveCalcFileSize("C:\\Users\\hkhk3\\Desktop\\Java\\project\\assignment\\src\\com\\huawei\\classroom\\student\\h57\\test_dir");
		if(length==132) {
			System.out.println("pass ");
		}
		System.out.println(length);
	}

}
