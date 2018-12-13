package com.jt.web.test;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients; 
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHpptClient {
    @Test
	public void testGET() throws ClientProtocolException, IOException{
    	//定义请求对象
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    //定义请求网址
	    String url = "http://tmooc.cn";
	    //定义请求对象
	    HttpGet get = new HttpGet(url);
	    //发起请求
	    CloseableHttpResponse response = httpClient.execute(get);
	    //判断请求是否正确
	    if(response.getStatusLine().getStatusCode()==200){
	    	//获取访问结果
	    	String result = EntityUtils.toString(response.getEntity());
	    	System.out.println(result);
	    }
	    }
	    @Test
		public void testPOSt() throws ClientProtocolException, IOException{
	    	//定义请求对象
		    CloseableHttpClient httpClient = HttpClients.createDefault();
		    //定义请求网址
		    String url = "http://tmooc.cn";
		    //定义请求对象
		    HttpGet get = new HttpGet(url);
		    HttpPost post= new HttpPost(url);
		    
		    //发起请求
		    CloseableHttpResponse response = httpClient.execute(post);
		   
		    //判断请求是否正确
		    if(response.getStatusLine().getStatusCode()==200){
		    	//获取访问结果
		    	String result = EntityUtils.toString(response.getEntity());
		    	System.out.println(result);		    	
		    }
	    
   }
	
	
}
