package com.druiz.bs4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix = "simple")
public class myProperties {
    @Value("${simple.VAR1}")
    private String var1;
    @Value("${simple.VAR2}")
    private int var2;
    @Value("${var3}")
    private String var3;

    public String getVar1() {return var1;}

    public void setVar1(String var1) {this.var1 = var1;}

    public int getMyVar2() {return var2;}

    public void setMyVar2(int myVar2) {this.var2 = myVar2;}

    public String getvar3() {return var3;}

    public void setvar3(String var3) {this.var3 = var3;}

    @Override
    public String toString() {
        return "myProperties{" +
                "var1='" + var1 + '\'' +
                ", var2=" + var2 +
                ", myVar3=" + var3 +
                '}';
    }
}
