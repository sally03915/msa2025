package com.company.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.userservice.dto.UserDto;
import com.company.userservice.jpa.UserEntity;
import com.company.userservice.jpa.UserRepository;
import com.company.userservice.vo.ResponseOrder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;  //##
    
    //##
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
		super();
		this.userRepository = userRepository;
		this.encoder = encoder;
	}



	@Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd( encoder.encode( userDto.getPwd()  ) );  //##

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);

        return returnUserDto;
    }
	
	   @Override
	    public UserDto getUserByUserId(String userId) {
	        UserEntity userEntity = userRepository.findByUserId(userId);

	        if (userEntity == null)
	            throw new UsernameNotFoundException("User not found");

	        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
	        List<ResponseOrder> orders= new ArrayList<>();
	        userDto.setOrders(orders);

	        return userDto;
	    }

	    @Override
	    public Iterable<UserEntity> getUserByAll() {
	        return userRepository.findAll();
	    }	
	
	
}
 
