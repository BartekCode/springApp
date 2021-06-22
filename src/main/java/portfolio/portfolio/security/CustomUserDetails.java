package portfolio.portfolio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import portfolio.portfolio.model.Role;
import portfolio.portfolio.model.User;
import portfolio.portfolio.adapter.SqlUserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetails implements UserDetailsService {

    private SqlUserRepository userRepository;

    @Autowired
    public CustomUserDetails(SqlUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user==null)
            throw new UsernameNotFoundException("User not found");


        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        convertAuthorities(Role.USER));
                        return userDetails;

    }

    private Set<GrantedAuthority> convertAuthorities(Role role) {
        Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
            authorities.add(new SimpleGrantedAuthority(Role.USER.toString()));

        return authorities;
    }


}
