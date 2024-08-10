package com.ecommerce.ecommerceapi.login.security.services;

import com.ecommerce.ecommerceapi.login.model.User;
import com.ecommerce.ecommerceapi.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()
                                    -> new UsernameNotFoundException("User not found with user name: " + username));
        return UserDetailsImpl.build(user);
    }
}
