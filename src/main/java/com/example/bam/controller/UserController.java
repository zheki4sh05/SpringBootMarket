package com.example.bam.controller;

import com.example.bam.configuration.JWTTokenProvider;
import com.example.bam.dto.AuthRequestDto;
import com.example.bam.dto.UserDto;
import com.example.bam.entity.User;
import com.example.bam.mapper.UserMapper;
import com.example.bam.repository.UserRepository;
import com.example.bam.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    // use constructor injection
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<User> registration(@RequestBody UserDto dto){
        User userDtoToUser = userMapper.userDtoToUser(dto);// Dtos in controller
        User user = userService.save(userDtoToUser);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto dto){
        UserDetails userDetails = userService.loadUserByUsername(dto.getUsername());

        // make as a bean
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(dto.getPassword(), userDetails.getPassword())){ // all password validation must be in service
            String token = tokenProvider.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findUserById (@PathVariable Long id) {
        User user = userService.findUserById(id); // Dtos in controller
        return ResponseEntity.ok(user); // consider handling exceptions
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<User> findUserByUsername (@PathVariable String username) {
        User user = (User) userService.loadUserByUsername(username); // Dtos in controller
        return ResponseEntity.ok(user); // consider handling exceptions such as UserNotFoundException
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll(); // add pagination
        return ResponseEntity.ok(users);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@RequestBody UserDto dto,
                           @PathVariable Long id) {
        return userRepository.findUserById(id) // all mappings in service, do  not forget about dtos
                .map(user -> {
                    user.setName(dto.getName()); // your username is important credential, so in this case like a password changing perform in another endpoint with logout action
                    user.setUsername(dto.getUsername());
                    user.setPassword(dto.getPassword()); // password changing must  perform in another API Endpoint with logout action
                    // in addition, password manipulations do with old password and both newValue and confirmValue
                    // in this approach, confirm exception handling and messages
                    return userService.save(user);
                }).orElseThrow();
    }

    @DeleteMapping("/deleteUserById/{id}")
    public HttpStatus delete (@PathVariable Long id) {
         userService.delete(id);
        // if not OK?
        return HttpStatus.OK;
    }
}
