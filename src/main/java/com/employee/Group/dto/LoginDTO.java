package com.employee.Group.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginDTO {
    private String username;
    private String password;
}
