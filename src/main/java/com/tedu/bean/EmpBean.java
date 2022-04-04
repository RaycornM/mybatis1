package com.tedu.bean;

import java.util.Date;

public class EmpBean {
	private int id;
	private String name;
	private int sex;
	private double salary;
	private int deparmentid;
	private int m_id;
	private Date createDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getDeparmentid() {
		return deparmentid;
	}
	public void setDeparmentid(int deparmentid) {
		this.deparmentid = deparmentid;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
		return "EmpBean [id=" + id + ", name=" + name + ", sex=" + sex + ", salary=" + salary + ", deparmentid="
				+ deparmentid + ", m_id=" + m_id + ", createDate=" + createDate + "]";
	}
	
	
}
