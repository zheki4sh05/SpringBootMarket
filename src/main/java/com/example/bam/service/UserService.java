package com.example.bam.service;

import com.example.bam.entity.Role;
import com.example.bam.entity.User;
import com.example.bam.exception.UserNotFoundException;
import com.example.bam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User findUserById (Long id) {
        return userRepository.findUserById(id).orElseThrow();
    }

    public List<User> findAll () {
        return userRepository.findAll();
    }
    public User save (User user) {
        user.setRole(Set.of(Role.USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        log.info("Save new {}", user);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findByUsername = userRepository.findUserByUsername(username);
        return findByUsername.orElseThrow(UserNotFoundException::new);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("Delete user");
    }


}
