package com.connor.jvm;

public class TestGC {


    public static void main(String[] args) {

        while (true){
            test();
        }
    }

    private static void test(){
        byte[][] allocation=new byte[11][];
        allocation[0] =new byte[1024 * 1024 * 100];
        allocation[2] =new byte[1024 * 1024 * 100];
        allocation[1] =new byte[1024 * 1024 * 100];
        allocation[4] =new byte[1024 * 1024 * 100];
        allocation[3] =new byte[1024 * 1024 * 100];
        allocation[5] =new byte[1024 * 1024 * 100];
        allocation[6] =new byte[1024 * 1024 * 100];
        allocation[7] =new byte[1024 * 1024 * 100];
        allocation[8] =new byte[1024 * 1024 * 100];
        allocation[9] =new byte[1024 * 1024 * 100];
        allocation[10] =new byte[1024 * 1024 * 100];
    }
}
