package com.workmotion.app.document.util;

import com.workmotion.app.member.MemberDTO;

public class Pager {
	
	private Long startRow;
	private Long lastRow;
	private Long perPage = 10L;//몇개씩 조회
	private Long page;
	
	private Long totalPage;
	
	private Long startNum;
	private Long lastNum;	
	
	private boolean start;
	private boolean last;
	
	private String search;
	private String kind;
	
	private Long member_id; 

	
	
	//페이지게시물 1~10 ,11~20 계산식  
		public void makeRow()throws Exception{
			this.startRow = (this.getPage()-1)*this.getPerPage()+1;
			this.lastRow = this.getPage()*this.getPerPage();
			
		}
		
		public void makeNum(Long totalCount)throws Exception{
			//검색결과가 없을때 페이지 번호 1로하기  
			if(totalCount<1) {
				totalCount=1L;
			}
			//총페이지 
			totalPage = totalCount/this.getPerPage();
			if(totalCount%this.getPerPage() !=0) {
				totalPage++;
			}
			this.setTotalPage(totalPage);
			
			//총페이지로 총 블럭 구하기
			Long perBlock = 5L;
			Long totalBlock = totalPage/perBlock;
			if(totalPage%perBlock !=0) {
				totalBlock++;
			}
			
			//현재페이지로 현재 블럭
			Long curBlock = this.getPage()/perBlock;
			if(this.getPage()%perBlock !=0) {
				curBlock++;
			}
			
			//현재블럭번호로 시작번호 끝번호
			lastNum = curBlock*perBlock;
			startNum = lastNum-perBlock+1;
			
			this.setLastNum(lastNum);
			this.setStartNum(startNum);
			//이전유무
			if(curBlock == 1) {
				this.setStart(true);
			}
			
			if(curBlock == totalBlock) {
				this.setLast(true);
				this.setLastNum(totalPage);
			}
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

		
		


	

	public Long getMember_id() {
			return member_id;
		}

		public void setMember_id(Long member_id) {
			this.member_id = member_id;
		}

	public Long getStartRow() {
		return startRow;
	}
	public void setStartRow(Long startRow) {
		this.startRow = startRow;
	}
	public Long getLastRow() {
		return lastRow;
	}
	public void setLastRow(Long lastRow) {
		this.lastRow = lastRow;
	}
	public Long getPerPage() {
		return perPage;
	}
	public void setPerPage(Long perPage) {
		this.perPage = perPage;
	}
	public Long getPage() {
		
		if(this.page==null || this.page < 1 ) {
			this.page = 1L;
		}
		return page;
		
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}
	public Long getStartNum() {
		return startNum;
	}
	public void setStartNum(Long startNum) {
		this.startNum = startNum;
	}
	public Long getLastNum() {
		return lastNum;
	}
	public void setLastNum(Long lastNum) {
		this.lastNum = lastNum;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean isLast() {
		return last;
	}
	public void setLast(boolean last) {
		this.last = last;
	}
	public String getSearch() {
		if(this.search == null) {
			this.search = "";
		}
		this.search = search;
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}

}
