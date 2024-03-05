package com.workmotion.app.util;

import org.springframework.stereotype.Component;

@Component
public class Pager {
	
	private Long startRow;
	private Long lastRow;
	private Long perPage=5L;
	private Long page;
	
	private Long totalPage;
	private Long totalCount;
	private Long startNum;
	private Long lastNum;

	private boolean start;
	private boolean last;
	
	private String search;
	private String kind;
	
	private Long count;

	
	
	public void makeRow() throws Exception {
		this.startRow = ((this.getPage()-1)*this.getPerPage())+1;
		this.lastRow =  this.getPage()*this.getPerPage();
	}
	
	public void makePage(Long totalCount)throws Exception {
		totalPage=totalCount/this.getPerPage();
		if(totalCount%this.getPerPage()!=0) {
			totalPage++;
		}
		
		Long totalBlock = 0L;
		Long perBlock = 10L;
		totalBlock = totalPage/perBlock;
		if(totalPage%perBlock!=0) {
			totalBlock++;
		}
		Long curBlock = 0L;
		curBlock = this.getPage()/perBlock;
		if(this.getPage()%perBlock!=0) {
			curBlock++;
		}
		
		Long startNum = 0L;
		Long lastNum = curBlock*perBlock;
		startNum = lastNum-perBlock+1;

		if(totalPage<perBlock) {
			lastNum =totalPage;
		}

		this.setStartNum(startNum);
		this.setLastNum(lastNum);
		
		
		if(curBlock==1) {
			this.setStart(true);
		}
		if(curBlock==totalBlock) {
			this.setLastNum(totalPage);
			this.setLast(true);
		}
		
		
		
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
		if(this.page==null||this.page<1) {
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
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
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
		if(this.search==null) {
			this.search ="";
      }
		return search;
	}

	@Override
	public String toString() {
		return "Pager [startRow=" + startRow + ", lastRow=" + lastRow + ", perPage=" + perPage + ", page=" + page
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", startNum=" + startNum + ", lastNum="
				+ lastNum + ", start=" + start + ", last=" + last + "]";
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

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
	
	
	
}
