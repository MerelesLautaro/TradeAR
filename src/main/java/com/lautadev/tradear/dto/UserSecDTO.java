package com.lautadev.tradear.dto;

import com.lautadev.tradear.model.UserSec;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSecDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;

    public static UserSecDTO fromUser(UserSec userSec){
        if(userSec == null){
            return null;
        }

        return new UserSecDTO(
                userSec.getId(),
                userSec.getName(),
                userSec.getLastname(),
                userSec.getEmail()
        );
    }
}
