package edu.mipt.backend.services;

import edu.mipt.backend.Responses.UserResponse;
import edu.mipt.backend.model.User;
import edu.mipt.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService {

    public static class UserAlreadyExists extends Exception{};

    public static class IncorrectPassword extends Exception{};

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    protected void initialize() {
        userRepository.save(new User(
                "user",
                "user@email.com",
                passwordEncoder.encode("123"),
                "ROLE_USER"));
        userRepository.save(new User(
                "admin",
                "admin@email.com",
                passwordEncoder.encode("123"),
                "ROLE_ADMIN"));
    }

    @Transactional
    public User register(String username, String email, String password) throws UserAlreadyExists {
        if (null != userRepository.findOneByUsername(username)) {
            throw new UserAlreadyExists();
        } else {
            User user = new User(username, email, passwordEncoder.encode(password), "ROLE_USER");
            userRepository.save(user);
            return user;
        }
    }

    @Transactional
    public String login(String username, String password) throws UsernameNotFoundException, IncorrectPassword {
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        if (passwordEncoder.matches(password, user.getPassword())) {
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            userRepository.save(user);
            return token;
        } else {
            throw new IncorrectPassword();
        }
    }

//    @Override
//    public Optional findByToken(String token) {
//        Optional<User> user = userRepository.findByToken(token);
//        if(user.isPresent()){
//            User user1 = user.get();
//            User user = new User(user1.getUsername(), user1.getPassword(), true, true, true, true,
//                    AuthorityUtils.createAuthorityList("USER"));
//            return Optional.of(user);
//        }
//        return  Optional.empty();
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findOneByUsername(username);
//        if(user == null) {
//            throw new UsernameNotFoundException("user not found");
//        }
//        return user;
//    }
}
