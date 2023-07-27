package com.employee.Group.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class RegisterDTO {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Role cannot be blank")
    private String role;
}
