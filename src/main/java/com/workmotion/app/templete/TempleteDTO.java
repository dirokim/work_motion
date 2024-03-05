package com.workmotion.app.templete;

import java.util.Map;

import com.workmotion.app.department.DepartmentDTO;
import com.workmotion.app.member.MemberDTO;

public class TempleteDTO {
	
	private Long id;
	private String name;
	private String file_nm;
	private String item;	
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFile_nm() {
		return file_nm;
	}
	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}
	

}
