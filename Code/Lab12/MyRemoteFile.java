package com.huawei.classroom.student.h62;

import java.io.File;
import java.io.FileOutputStream;

public class MyRemoteFile {
	private String remotepath;
	private String absolute_path;
	private String relative_path;
	public boolean isfile;

	public MyRemoteFile(MyHost host, String string) {
		remotepath = host.getRemotefilepath();
		relative_path = string;
		absolute_path = remotepath.substring(0, remotepath.length() - 1) + relative_path;
		if (absolute_path.endsWith("/"))
			isfile = false;
		else
			isfile = true;
	}

	public MyRemoteFile() {
	}

	public MyRemoteFile[] dirByNameAsc() {
		File file = new File(absolute_path);
		File[] fileList = file.listFiles();
		MyRemoteFile[] ans = new MyRemoteFile[fileList.length];
		int p = 0;
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isDirectory()) {
				String abspath = fileList[i].getPath();
				String repath = "/" + abspath.substring(remotepath.length());
				ans[p] = new MyRemoteFile();
				ans[p].remotepath = this.remotepath;
				ans[p].absolute_path = abspath;
				ans[p].relative_path = repath + "/";
				ans[p].isfile = false;
				p++;
			}
		}
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].isFile()) {
				String abspath = fileList[i].getPath();
				String repath = "/" + abspath.substring(remotepath.length());
				ans[p] = new MyRemoteFile();
				ans[p].remotepath = this.remotepath;
				ans[p].absolute_path = abspath;
				ans[p].relative_path = repath;
				ans[p].isfile = true;
				p++;
			}
		}
		return ans;
	}

	public boolean isDirectory() {
		return !isfile;
	}

	public boolean isFile() {
		return isfile;
	}

	public Object getPathFileName() {
		String ans = relative_path.replace("\\", "/");
		return ans;
	}

	public void writeByBytes(byte[] bytes) {
		String source = absolute_path;
		File targetfile = new File(source);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(targetfile);
			fos.write(bytes);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long length() {
		String source2 = absolute_path;
		File fileclass = new File(source2);
		if (!fileclass.exists()) {
			return 0;
		}
		return fileclass.length();
	}

	public void delete() {
		String source2 = absolute_path;
		File fileclass = new File(source2);
		fileclass.delete();
	}

	public boolean exists() {
		String source2 = absolute_path;
		File fileclass = new File(source2);
		return fileclass.exists();
	}
}
