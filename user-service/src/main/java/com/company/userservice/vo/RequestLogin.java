package com.company.userservice.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestLogin {
    @NotNull(message = "이메일은 빈칸이면 안됩니다..")
    @Size(min = 2, message = "이메일은 2글자 이상입니다.")
    @Email
    private String email;

    @NotNull(message = "비밀번호는 빈칸이면 안됩니다.")
    @Size(min = 8, message = "비밀번호는 8자 이상입니다.")
    private String password;
}