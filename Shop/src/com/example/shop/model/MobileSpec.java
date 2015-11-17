package com.example.shop.model;

public class MobileSpec {

	private String mid ,os, sim, display, memory, camera, battery;

	public MobileSpec(String mid, String os, String sim, String display,
			String memory, String camera, String battery) {
		super();
		this.mid = mid;
		this.os = os;
		this.sim = sim;
		this.display = display;
		this.memory = memory;
		this.camera = camera;
		this.battery = battery;
	}

	public MobileSpec() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getCamera() {
		return camera;
	}

	public void setCamera(String camera) {
		this.camera = camera;
	}

	public String getBattery() {
		return battery;
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}
	
	
}
