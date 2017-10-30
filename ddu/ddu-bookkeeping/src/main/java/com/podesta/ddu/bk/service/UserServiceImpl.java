package com.podesta.ddu.bk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.podesta.ddu.bk.dao.IUserDao;
import com.podesta.ddu.bk.model.User;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	public User getByID(int id) {
		return userDao.getUserByID(id);
	}

}
