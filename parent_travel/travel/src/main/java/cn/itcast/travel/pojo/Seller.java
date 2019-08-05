package cn.itcast.travel.pojo;

import java.io.Serializable;

/**
 * 商家实体类
 */
public class Seller implements Serializable {
    private Integer sid;//商家id
    private String sname;//商家名称
    private String consphone;//商家电话
    private String address;//商家地址
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getConsphone() {
		return consphone;
	}
	public void setConsphone(String consphone) {
		this.consphone = consphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Seller [sid=" + sid + ", sname=" + sname + ", consphone="
				+ consphone + ", address=" + address + "]";
	}

   
}
