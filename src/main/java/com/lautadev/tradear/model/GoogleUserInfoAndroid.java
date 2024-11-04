package com.lautadev.tradear.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleUserInfoAndroid {
    private String id;
    private String email;
    private String name;
    private String lastname;
    private String pictureUrl;
}
