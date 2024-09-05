package org.bmach01.AcKeyAPI.service;

import org.bmach01.AcKeyAPI.dao.UserRepository;
import org.bmach01.AcKeyAPI.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  repository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        return user;
    }
}
