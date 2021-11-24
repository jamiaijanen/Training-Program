package com.example.trainingprogram.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.trainingprogram.domain.User;
import com.example.trainingprogram.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository krepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.krepository = userRepository;
	}

	// provides user information
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = krepository.findByUsername(username);
		UserDetails userdata = new org.springframework.security.core.userdetails.User(username, user.getPasswordHash(),
				AuthorityUtils.createAuthorityList(user.getRole()));

		return userdata;
	}
}