package com.connor.spring.bean;

public class ConnorBean {


    private String city;

    private String counrty;

    private void init(){
        System.out.println("ConnorBean-init");
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounrty() {
        return counrty;
    }

    public void setCounrty(String counrty) {
        this.counrty = counrty;
    }
}
