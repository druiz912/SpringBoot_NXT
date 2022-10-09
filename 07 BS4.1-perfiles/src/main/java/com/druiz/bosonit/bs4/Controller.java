package com.druiz.bosonit.bs4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
        @Value("${URL}")
        String url;

        @Value("${PASSWORD}")
        String password;

        @Autowired
        ConfigurationProperties configurationProperties;

        @Autowired
        ProfileInterface profileInterface;

        @GetMapping("/direction")
        public String getUrlAndPassword(){
            return "The URL is: " + url+ " and the password is : " + password;
        }

        @GetMapping("/myconfiguration")
        public String getMyValues(){
            return "The Value1 is: " + configurationProperties.getValor1()+ " and the Value2 is : " + configurationProperties.getValor2();
        }

        @GetMapping("/profile")
        public String getProfile(){
            return profileInterface.miFuncion();
        }
}
