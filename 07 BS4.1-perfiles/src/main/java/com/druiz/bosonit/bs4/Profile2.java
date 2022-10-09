package com.druiz.bosonit.bs4;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("profile2")
public class Profile2 implements ProfileInterface {
    private String profile = "perfil2";

    @Override
    public String miFuncion() {
        return this.profile;
    }
}
