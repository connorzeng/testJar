package com.connor.spring;

public class ClientImpl implements Client {

    @Override
    public void get(String d) {

    }

    @Override
    public void set(String h) {
        TestSpringContext t = new TestSpringContext();
        switch (t.a) {


        }
    }


    public static void main(String[] args) {

        Client client = new ClientImpl();
        client.set("");

    }
}
