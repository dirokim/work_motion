package com.workmotion.app.company;

import java.sql.Date;
import java.util.UUID;

public class CompanyDTO {

	private Long id;
	private String name;
	private String owner;
	private Long phone_num;
	private String info;
	private Date create_dt; 
	private String customerkey;
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
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Long getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(Long phone_num) {
		this.phone_num = phone_num;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getCreate_dt() {
		return create_dt;
	}
	public void setCreate_dt(Date create_dt) {
		this.create_dt = create_dt;
	}
	public String getCustomerkey() {
		return customerkey;
	}
	public void setCustomerkey(String customerkey) {
		this.customerkey = customerkey;
	}

	
}
