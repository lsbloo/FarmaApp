package com.poc.FarmaLoginService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long user_auth_id;

    public UserDTO(Long id) {
        setUser_auth_id(id);
    }
}
