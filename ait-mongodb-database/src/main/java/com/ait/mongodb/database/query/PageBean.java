package com.ait.mongodb.database.query;

public class PageBean {
   
	private int pageSize;
	private int page;
	
	public PageBean(){
		
	}
	
	public PageBean(int pageSize, int page) {
		this.pageSize = pageSize;
		this.page = page;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	
	
}
