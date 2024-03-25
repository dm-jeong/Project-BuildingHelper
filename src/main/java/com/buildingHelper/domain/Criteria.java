package com.buildingHelper.domain;


public class Criteria{
	
    private int page;	  // 현재 페이지 번호

    private int per_num;  // 한 페이지당 보여줄 게시글의 개수
    
    
    // 현재 페이지의 시작 항목 인덱스
    public int get_page_start() {
    	return (this.page-1)*per_num;
    }

	public Criteria() {}
	

	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page=page;

		
	}


	public int getPer_num() {
		return per_num;
	}


	public void setPer_num(int per_num) {
		this.per_num = per_num;
		
	}

}