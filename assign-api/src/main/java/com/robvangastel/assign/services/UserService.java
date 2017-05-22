package com.robvangastel.assign.services;

import com.robvangastel.assign.crypto.Crypto;
import com.robvangastel.assign.crypto.PasswordEncoder;
import com.robvangastel.assign.dao.IUserDao;
import com.robvangastel.assign.domain.User;
import com.robvangastel.assign.exception.AuthenticationException;
import com.robvangastel.assign.security.Credentials;
import com.robvangastel.assign.security.IdToken;
import com.robvangastel.assign.security.jwt.JwtHelper;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Rob on 23-4-2017.
 */

@Stateless
public class UserService implements Serializable {

	@Inject
	private IUserDao dao;

	@Inject
	@Crypto(Crypto.Type.BCRYPT)
	PasswordEncoder encoder;

	@Inject
	JwtHelper jwtHelper;

	public User findById(long id) {
		return dao.findById(id);
	}

	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	public List<User> findAll() {
		return dao.findAll();
	}

	public User create(User entity) throws Exception {
		if(dao.findByEmail(entity.getEmail()) == null) {
			entity.setPassword(encoder.encode(entity.getPassword()));
			return dao.create(entity);
		}
		return null;
	}

	public IdToken authenticate(Credentials credentials) {
		User user = dao.findByEmail(credentials.getUsername());
		if (user == null) {
			throw new AuthenticationException();
		}
		if (!encoder.matches(credentials.getPassword(), user.getPassword())) {
			throw new AuthenticationException();
		}
		return new IdToken(jwtHelper.generateToken(user));
	}

	public User update(User entity) throws Exception {
		return dao.update(entity);
	}

	public void delete(long id) throws Exception  {
		dao.deleteById(id);
	}
}
