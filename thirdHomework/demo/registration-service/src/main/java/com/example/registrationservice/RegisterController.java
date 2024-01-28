package com.example.registrationservice;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
//@RequestMapping("/register")
public class RegisterController {
    private final AuthService authService;
    protected UserRepository userRepository;

    public RegisterController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/users/{username}")
    public User byUsername(@PathVariable("username") String username) {

        User account = userRepository.findByUsername(username);

        if (account == null)
            throw new InvalidUserExcepion();
        else {
            return account;
        }
    }

    @RequestMapping(value = "/users/register", method = RequestMethod.POST)
    public ResponseEntity<String> byUsername(@RequestBody UserRegistrationRequest request) {
        try {
            User user = authService.register(request.getUsername(), request.getPassword(),
                    request.getRepeatPassword(), request.getName(), request.getSurname());
            return ResponseEntity.ok("User registered successfully!");
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }







}
