package com.bit.servlet.dao;

public class PhoneVo {
	private Long id;
	private String name ;
	private String hp;
	private String tel;
	
	// generator
	public PhoneVo() {
		// default generator
	}
	
	public PhoneVo(String name, String hp) {
		this.name = name;
		this.hp = hp;
	}
	
	public PhoneVo(String name, String hp, String tel) {
		this(name, hp);
		this.tel = tel;
	}
	
	public PhoneVo(Long id, String name, String hp, String tel) {
		this(name, hp, tel);
		this.id = id;
	}

	// getter / setter
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

	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
