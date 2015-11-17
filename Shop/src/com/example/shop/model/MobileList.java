package com.example.shop.model;

import java.io.Serializable;

public class MobileList implements Serializable{

	private String mid,name,model,price,imgurl;

	public MobileList() {
		// TODO Auto-generated constructor stub
	}

	public MobileList(String mid, String name, String model, String price,
			String imgurl) {
		super();
		this.mid = mid;
		this.name = name;
		this.model = model;
		this.price = price;
		this.imgurl = imgurl;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	
	
}
