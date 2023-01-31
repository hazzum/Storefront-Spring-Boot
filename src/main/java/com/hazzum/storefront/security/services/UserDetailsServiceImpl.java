package com.hazzum.storefront.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hazzum.storefront.entity.User;
import com.hazzum.storefront.rest.exceptionHandler.NotFoundException;
import com.hazzum.storefront.DAO.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUserName(username);
    if (user == null) throw new NotFoundException("No such user exists");
    return UserDetailsImpl.build(user);
  }

}
