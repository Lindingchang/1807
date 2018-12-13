package com.jt.web.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.methods.HttpPost;

public class testlist {
    public static void main(String[] args) {
		
    	ArrayList<Object> list = new ArrayList<>();
        HttpPost httpPost = new HttpPost();
        
        HashMap<Object, Object> map = new HashMap<>();
    	map.put("a", "123");
    	map.put("b", "asd");
    	map.put("c", "asd");
    	Set<Entry<Object, Object>> entrySet = map.entrySet();
    	//System.out.println(map);
    	//System.out.println(entrySet);
    	for (Entry<Object, Object> entry : entrySet) {
			System.out.println(entry.getKey());
		}
	}
}  
