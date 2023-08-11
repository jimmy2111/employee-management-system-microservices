package com.eic.springboot.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eic.springboot.dto.LoginDto;
import com.eic.springboot.dto.UserDto;
import com.eic.springboot.entity.Role;
import com.eic.springboot.entity.User;
import com.eic.springboot.exception.UserAPIException;
import com.eic.springboot.repository.RoleRepository;
import com.eic.springboot.repository.UserRepository;
import com.eic.springboot.security.JwtTokenProvider;
import com.eic.springboot.service.AuthService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);
   // private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String login(LoginDto loginDto) {
        //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        LOGGER.info("Authentication Set Successfull");
        String token = jwtTokenProvider.generateToken(loginDto.getEmail());
        LOGGER.info(" JWT Token Generated for authenticated User");
        return token;
    }

    @Override
    public String register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())){
            LOGGER.error("User Email Already Exists");
            throw new UserAPIException("User Email Already Exists");
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_USER").get();
        LOGGER.info("Assigned Role to User");
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        LOGGER.info("User Registered Successfully");
        return "User Registered Successfully";
    }

	@Override
	public List<String> getRoles(String username) {
		User user = userRepository.findByEmail(username).get();
		List<Role> roles = user.getRoles().stream().toList();
		List<String> userRoles = roles.stream().map(Role::getName).collect(Collectors.toList());
		return userRoles;
	}
}
