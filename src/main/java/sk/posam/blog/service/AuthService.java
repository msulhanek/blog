package sk.posam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sk.posam.blog.dto.LoginRequest;
import sk.posam.blog.dto.RegisterRequest;
import sk.posam.blog.exception.PostNotFondException;
import sk.posam.blog.exception.SpringBlogException;
import sk.posam.blog.model.User;
import sk.posam.blog.repository.UserRepository;
import sk.posam.blog.security.JwtProvider;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public boolean register(RegisterRequest registerRequest) {
        Optional<User> findUser = userRepository.findByUsername(registerRequest.getUsername());
        if(!findUser.isEmpty()){
            return false;
        }
        User user = new User();
        user.setName(registerRequest.getName());
        user.setSurename(registerRequest.getSurename());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setRegisterDate(Instant.now());
        user.setProfilePicture("no-image.jpg");
        user.setNote("No text");
        user.setNumberOfPosts(0);
        userRepository.save(user);
        return true;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws SpringBlogException {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
