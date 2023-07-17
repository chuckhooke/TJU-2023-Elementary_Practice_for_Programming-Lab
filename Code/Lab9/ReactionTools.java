package com.huawei.classroom.student.h59;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

public class ReactionTools {

	/**
	 * 根据reactionFile给出的一系列反应， 判断一个体系中根据init物质，判断出最后可能都存在什么物质
	 * @param reactionFile 体系中初始反应物
	 * @param initComponents 体系中初始反应物
	 * @return 最后体系中存在的全部物质
	 */
	public Set<String> findAllComponents(String reactionFile,Set<String> initComponents){
		Set<String> react = new HashSet<String>();
		Set<String> result = new HashSet<String>();
		react = initComponents;
		int prenum = 0;
		int desnum = 0;
		do {
			prenum = react.size();
			result = traversereactionFile(reactionFile, react);
			desnum = result.size();
			react = result;
		}while(prenum != desnum);
		return result;
	}
	
	public Set<String> traversereactionFile(String reactionFile,Set<String> initComponents){
		Set<String> result = new HashSet<String>();
		for(String com : initComponents) {
			result.add(com);
		}
		try (BufferedReader br = 
				new BufferedReader(new InputStreamReader(new FileInputStream(reactionFile),"UTF-8"))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	        	if(line.charAt(0)=='#') continue;
//	        	line.replace(" ", "");
	        	String [] component = line.split(" = ");
	        	String [] reactant = component[0].split(" \\+ ");
	        	String [] product = component[1].split(" \\+ ");
	        	boolean flag_re = true;
	        	boolean flag_pr = true;
	        	for(String re: reactant) {
	        		if(!initComponents.contains(re)) {
	        			flag_re = false;
	        		}
	        	}
	        	for(String pr: product) {
	        		if(!initComponents.contains(pr)) {
	        			flag_pr = false;
	        		}
	        	}
	        	if(flag_re) {
	        		for(String pr: product) {
		        		result.add(pr);
		        	}
	        	}
	        	if(flag_pr) {
	        		for(String re: reactant) {
		        		result.add(re);
		        	}
	        	}
	        }
	    }
		catch (Exception e) {
	        e.printStackTrace();
	    }
		return result;
	}
		
}


