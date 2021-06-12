package com.medshare.services;

import com.medshare.models.ApplicationUser;
import com.medshare.models.User;
import com.medshare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository applicationUserRepository;

    @Override
    public
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user =  applicationUserRepository.findByUsername(userName);
        System.out.println("User found? " + user + " from username " + userName);
        if (user!=null){
            return new ApplicationUser(user);
        }
        throw new UsernameNotFoundException("User: " + userName+ " does not exist");
    }
}
