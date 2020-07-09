package com.dtucdio3.digitalshop2.entity;

import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch(first = "password", second = "confirmPassword", message = "Mật khẩu không khớp!")
public class Register {
    @NotBlank(message = "Tên không được để trống!")
    @Size(max = 40)
    private String name;

    @NotBlank(message = "Username không được để trống!")
    @Size(max = 15)
    private String username;

    @NotBlank(message = "Email không được để trống!")
    @Size(max = 40)
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống!")
    @Size(max = 100)
    private String password;

    @NotBlank(message = "Xát nhận mật khẩu không được để trống!")
    @Size(max = 100)
    private String confirmPassword;

    public Register() {
    }

    public Register(@NotBlank(message = "Tên không được để trống!") @Size(max = 40) String name, @NotBlank(message = "Username không được để trống!") @Size(max = 15) String username, @NotBlank(message = "Email không được để trống!") @Size(max = 40) @Email(message = "Email không hợp lệ") String email, @NotBlank(message = "Mật khẩu không được để trống!") @Size(max = 100) String password, @NotBlank(message = "Xát nhận mật khẩu không được để trống!") @Size(max = 100) String confirmPassword) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
