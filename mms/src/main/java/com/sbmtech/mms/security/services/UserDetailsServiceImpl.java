package com.sbmtech.mms.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sbmtech.mms.models.User;
import com.sbmtech.mms.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String mobileNo) throws UsernameNotFoundException {
        User user = userRepository.findByMobileNo(mobileNo)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with mobileNo: " + mobileNo));

        return UserDetailsImpl.build(user);
    }

}
