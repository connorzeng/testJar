package com.connor.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentmapTest {


    public static void main(String[] args) throws InterruptedException {

        ConcurrentMap<String,Integer> goods = new ConcurrentHashMap<>();
        goods.put("goods",50);

        List<Thread> consumers = new ArrayList<Thread>();
        for (int i = 0; i<1000; i++){
            Thread consumer = new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = goods.get("goods");
                    if (i > 0){
                        //模拟非原子性的事物
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        goods.put("goods",goods.get("goods") - 1);
                    }
                }
            });
            consumers.add(consumer);
        }

        for (Thread thread : consumers){
            thread.start();

        }
        for (Thread thread : consumers){
            thread.join();
        }


        System.out.println(goods.get("goods"));
    }


}
