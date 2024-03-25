package com.buildingHelper.domain;

public class Vote_participant 
{
	private Integer vp_id; // '투표참여' 아이디
	private Integer vp_v_id; // 투표참여한 투표의 아이디
	private String vp_result; // 투표에서 선택한 결과
	private int vp_house_id; // 투표에 참여한 세대아이디
	
	public int getVp_house_id() {
		return vp_house_id;
	}

	public void setVp_house_id(int vp_house_id) {
		this.vp_house_id = vp_house_id;
	}

	public Vote_participant() {
		super();
	}

	public Integer getVp_id() {
		return vp_id;
	}

	public void setVp_id(Integer vp_id) {
		this.vp_id = vp_id;
	}

	public Integer getVp_v_id() {
		return vp_v_id;
	}

	public void setVp_v_id(Integer vp_v_id) {
		this.vp_v_id = vp_v_id;
	}

	public String getVp_result() {
		return vp_result;
	}

	public void setVp_result(String vp_result) {
		this.vp_result = vp_result;
	}
}
