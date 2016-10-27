package nl.avansc1.facturatie.service;

import nl.avansc1.facturatie.model.administration.CustomUserDetails;
import nl.avansc1.facturatie.model.administration.User;
import nl.avansc1.facturatie.repository.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsService implements UserDetailsService {

    private UserDAO userDAO;

    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(username);

        if (null == user) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return new CustomUserDetails(user);
        }
    }

}