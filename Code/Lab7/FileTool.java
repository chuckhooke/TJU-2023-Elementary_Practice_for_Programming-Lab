package com.huawei.classroom.student.h57;

import java.io.File;

public class FileTool {

	/*
	 * 统计一个目录下所有文件大小的加和
	 */
	public long recursiveCalcFileSize(String homeDir) {
		long totalsize = 0;
		File dir = new File (homeDir);
		if (!dir.exists() || !dir.isDirectory()) {
			return 0;
		}
		String[] files = dir.list();
		for (int i = 0; i < files.length; i++) {
			File file = new File(dir, files[i]);
			if (file.isFile()) {
				totalsize += file.length();
//				System.out.println(file.length());
			} else {
				totalsize += recursiveCalcFileSize(String.valueOf(file)); // 对于子目录,进行递归调用
			}
		}
		return totalsize;
	}
	

}
