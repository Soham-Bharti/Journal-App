package net.SohamDB.journalApp.controller;

import net.SohamDB.journalApp.dto.UserUpdateDTO;
import net.SohamDB.journalApp.entity.User;
import net.SohamDB.journalApp.repository.UserRepository;
import net.SohamDB.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User userInDb = userService.findByUserName(currentUsername);

        if (userInDb == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (userUpdateDTO.getUserName() != null && !userUpdateDTO.getUserName().isBlank()) {
            userInDb.setUserName(userUpdateDTO.getUserName());
        }

        if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(userUpdateDTO.getPassword());
            userInDb.setPassword(encodedPassword);
        }

        userService.saveUser(userInDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
