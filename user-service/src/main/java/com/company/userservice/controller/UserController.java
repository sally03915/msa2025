package com.company.userservice.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.userservice.dto.UserDto; 
import com.company.userservice.service.UserService;
import com.company.userservice.vo.RequestUser;
import com.company.userservice.vo.ResponseUser;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user-service")
@Slf4j
public class UserController {
	@Autowired
	private Greeting greeting;
	
	private Environment env;
    private UserService userService;  //추가

    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }  //추가

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
    	log.info(",,,,,,,,,,,,,,,,,,,,," + user);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

	@GetMapping("/health_check")
	public String hello() {
		return "..... User Service....";
	}
	@GetMapping("/welcome")
	public String welcome() {
		return env.getProperty("greeting.message");
	}
	
	@GetMapping("/welcome2") public String welcome2() {
		return greeting.getMessage();
	}
 
	
}
