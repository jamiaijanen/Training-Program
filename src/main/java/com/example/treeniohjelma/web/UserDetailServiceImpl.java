package com.example.treeniohjelma.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.treeniohjelma.domain.User;
import com.example.treeniohjelma.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository krepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository kayttajaRepository) {
		this.krepository = kayttajaRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User kayttajaTunnus = krepository.findByUsername(username);
		UserDetails kayttajaNimi = new org.springframework.security.core.userdetails.User(username,
				kayttajaTunnus.getPasswordHash(), AuthorityUtils.createAuthorityList(kayttajaTunnus.getRole()));

		return kayttajaNimi;
	}
}