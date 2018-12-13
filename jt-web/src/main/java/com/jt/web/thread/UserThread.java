package com.jt.web.thread;

import com.jt.common.po.User;

public class UserThread {
    private static ThreadLocal<User> userThread = new ThreadLocal<>();
	public static void set(User user){
		userThread.set(user);
	}
	public static User get(){
		return userThread.get();
	}
	
	//内存泄漏问题，GC无权限回收
	public static void remove(){
		userThread.remove();
	}
}
