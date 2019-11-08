package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.model.User;
import tacos.data.JpaUserRepository;

import java.util.Objects;

/**
 * Created by Marina Baban
 */

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

    private JpaUserRepository userRepository;

    @Autowired
    public UserRepositoryUserDetailsService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
