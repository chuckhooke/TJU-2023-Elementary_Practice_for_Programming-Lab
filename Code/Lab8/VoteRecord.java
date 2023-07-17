package com.huawei.classroom.student.h58;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class VoteRecord {
	/**
	 * fileName是一个投票的明细记录，里面逐行存放了 投票的时间（yyyy-MM-dd HH:mm:ss 格式） +\t+投票的微信ID+\t+候选人
	 * 存放按时间递增（但是可能出现同一秒出现若干条记录的情况）
	 * 现在需要完成投票统计的过程，具体要求如下：
	 * 1个微信ID 1分钟内 最多投1票 多余的票数无效
	 * 1个微信ID 10分钟内 最多只能投5票 多余的票无效
	 * 其中微信ID不固定，候选人姓名不固定
	 * 测试的时候要求10万行记录处理时间不超过3秒 
	 * @param fileName
	 * @return 返回一个map，其中key是候选人名字，value的票数
	 */
	public Map<String, Integer> calcRecording(String fileName) {
	    Map<String, Integer> result = new HashMap<>();
	    Map<String, Set<java.util.Date>> votes = new HashMap<>();
	    
	    try (BufferedReader br = 
				new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            String[] parts = line.split("\t");
	            java.util.Date time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(parts[0]); 
	            String weixinId = parts[1];
	            String candidate = parts[2];
	            
	            // 获取最近五票的投票时间
	            if (!votes.containsKey(weixinId)) {
	   				votes.put(weixinId, new HashSet<>());
	   			}
	            Set <java.util.Date> fivelastVotes = votes.get(weixinId);
	            

	            java.util.Date earliest = null;
	            if(fivelastVotes.size() == 0) {
	            	 earliest = time;
	            }
	            for (java.util.Date date : fivelastVotes) {
	                if (earliest == null || date.before(earliest)) {
	                    earliest = date;
	                } 
	            }
	            
	            java.util.Date latest = null;
	            if(fivelastVotes.size() == 0) {
	            	 latest = time;
	            }
	            for (java.util.Date date : fivelastVotes) {
	                if (latest == null || date.after(latest)) {
	                	latest = date;
	                } 
	            }
	            
	            long curtime = time.getTime();
	            long earlytime = earliest.getTime();
	            long latetime = latest.getTime();
	            long diff1 = curtime - earlytime;  // 毫秒级差值
	            long diff2 = curtime - latetime;  // 毫秒级差值
	            
	            if(fivelastVotes.size() == 0) {
	            	 diff1 = 10000000;
	            	 diff2 = 10000000;
	            }
	           
	           //  如果不超限,增加票数
	            if(diff1 / (60 * 1000) >= 10 && diff2 / (60 * 1000) >= 1) {
	        	   if(fivelastVotes.size() < 5) {
	        		   fivelastVotes.add(time);
	        	   }
	        	   else {
	        		   fivelastVotes.remove(earliest);
	        		   fivelastVotes.add(time);
	        	   }
	        	   int count = 1;
		   			if (result.containsKey(candidate)) {
		   				count = result.get(candidate) + 1;
		   			}
		   			result.put(candidate, count);
	            }
	        }
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	    }
	    return result;
	 }

}
