package mx.com.jmsa.restaurant.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mx.com.jmsa.restaurant.entity.User;
import mx.com.jmsa.restaurant.entity.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByName(name);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return org.springframework.security.core.userdetails.User
        .withDefaultPasswordEncoder()
        .username(user.getName())
        .password(user.getPassword())
        .authorities(user.getRole()) // Assign default authority; customize as needed
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
    }
    
}
