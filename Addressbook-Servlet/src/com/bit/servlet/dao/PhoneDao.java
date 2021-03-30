package com.bit.servlet.dao;

import java.util.List;

public interface PhoneDao {
	public List<PhoneVo> getList();
	public List<PhoneVo> search(String keyword);
	public boolean insert(PhoneVo vo);
	public boolean delete(Long id);
}
