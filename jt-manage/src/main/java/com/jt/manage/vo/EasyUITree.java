package com.jt.manage.vo;

public class EasyUITree {
  
	private Long id;   //商品分类id
	private String text;   //商品分类名称
	private String state; //close 节点闭合
	
	//如果不加无参构造反射创建对象报错
	public EasyUITree() {
		super();
	}
	public EasyUITree(Long id, String text, String state) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
