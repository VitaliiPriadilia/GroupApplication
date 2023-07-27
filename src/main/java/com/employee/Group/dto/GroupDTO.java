package com.employee.Group.dto;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class GroupDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String token;
}
