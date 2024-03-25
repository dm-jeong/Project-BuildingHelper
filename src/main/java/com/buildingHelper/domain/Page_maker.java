package com.buildingHelper.domain;

// 페이징 처리 해줌
public class Page_maker {
   private Criteria cri;
   private int total_count;
   private int start_page;
   private int end_page;
   private boolean prev;
   private boolean next;
   private int display_num = 5;  // 블록 페이징 묶음 개수 설정 
   
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;  // count(*) 결과 dto에 저장하고 다음 코드 실행(전처리)
		calc_data();  // 전처리 실행
	}
   
	


	
	
	// 전처리
	private void calc_data(){
		
			
		end_page = (int) (Math.ceil(cri.getPage() / (double) display_num) * display_num); // 페이지 블록의 끝 [11~20] : 20
		
		start_page = (end_page - display_num) + 1; // 현재 블록의 시작 페이지 
		if(start_page <= 0) start_page=1;  // 첫 블록 시작은 1 
		
		
		// 전체 페이지 수에서 
		int temp_end_page = (int) (Math.ceil(total_count / (double) cri.getPer_num()));  // 전체 페이지 수 계산 [1][2][3] : 3
		
		
		
		if(end_page > temp_end_page) { 
			end_page = temp_end_page;    // 끝 페이지가 전체 페이지보다 크면 서로 같게 
		}
		
		/*
		temp_end_page: 30개의 데이터를 10개씩 보여주면 3페이지가 필요하므로 temp_end_page는 3이 됩니다.
		end_page: 만약 현재 페이지가 2번째 블록에 속한다면 (11번부터 20번까지의 데이터를 보여줄 때), end_page는 20이 됩니다.
		*/
		
		
		// 이전, 다음 활성화 여부
		prev = start_page == 1 ? false : true;  
		next = end_page * cri.getPer_num() < total_count ? true : false;
		
	}
	
	
	
	public int getStart_page() {
		return start_page;
	}
	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}
	public int getEnd_page() {
		return end_page;
	}
	public void setEnd_page(int end_page) {
		this.end_page = end_page;
	}
	
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplay_num() {
		return display_num;
	}
	public void setDisplay_num(int display_num) {
		this.display_num = display_num;
	}
	
	
}
