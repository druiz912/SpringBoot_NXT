package com.example.bs41.bean;

public class MyConfigurationBeanImp implements MyConfigurationBean{

    private String valor1;
    private String valor2;

    public MyConfigurationBeanImp(String valor1, String valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
        System.out.println("valor1: "+this.valor1+ " - valor2: "+this.valor2);
    }


    @Override
    public String getValor1() {
        return this.valor1;
    }

    @Override
    public String getValor2() {
        return this.valor2;
    }
}
