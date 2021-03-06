package nl.avansc1.facturatie.model.administration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by Robin on 27/10/2016.
 * Custom implementation of UserDetails interface.
 * Sets roles and username for authorization.
 *
 * @author Robin Valk
 * @version 1.0
 * @see User
 * @see UserDetails
 */
public class CustomUserDetails extends User implements UserDetails {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     * @param user
     */
    public CustomUserDetails(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(super.getRole());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }
}
