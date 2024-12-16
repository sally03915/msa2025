package com.company.userservice.dto;

import java.util.Date;
import java.util.List;

import com.company.userservice.vo.ResponseOrder;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String name;
    private String pwd;
    
    private String userId;
    private Date createdAt;

    private String decryptedPwd;
    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
//사용자에게 입력받을 값 - email, name, pwd (암호값, 복호값)    +  생성아이디, 생성날짜 + 주문리스트