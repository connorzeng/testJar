package com.connor.basic.thread;

public class TestVoilate {
    volatile int a = 1;
    int b = 2;

    public void change(){
        a = 3;
        b = a;
    }

    public void print(){
        if (a == 3 && b == 1){
            System.out.println("b="+b+";a="+a);
        }
    }

    public static void main(String[] args) {
        while (true){
            final TestVoilate test = new TestVoilate();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();

        }
    }
}