package com.example.demo.controller;

/*import com.example.demo.basicAuth.JwtProvider;
import com.example.demo.basicAuth.JwtResponse;*/
import com.example.demo.model.dto.pages.LoginForm;
import com.example.demo.model.User;
import com.example.demo.model.dto.pages.SignUpForm;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/api/auth/users")
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userService.getAll());
    }



   /* @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;*/

    @RequestMapping(value="/api/auth/signin", method={RequestMethod.OPTIONS,RequestMethod.GET})
    public String authenticateUser( ) {
        return "ok";
    }

    /*@PostMapping("/api/auth/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, userService.getIdByUsername(loginRequest.getUsername()),
                userService.getRoleByUsername(loginRequest.getUsername())));
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpForm signUpRequest) {
        if(userRepository.findByLogin(signUpRequest.getLogin())!=null) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }


        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getSurname(),
                signUpRequest.getLogin(), encoder.encode(signUpRequest.getPassword()));

        String role = signUpRequest.getRole();
        //Set<Role> roles = new HashSet<>();

       // strRoles.forEach(role -> {
//            switch(role) {
//                case "admin":
//                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                    roles.add(adminRole);
//
//                    break;
//                case "pm":
//                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                    roles.add(pmRole);
//
//                    break;
//                default:
//                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//                    roles.add(userRole);
//            }
//        });

        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.ok().body("User registered successfully!");
    }*/
}
