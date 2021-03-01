package com.connor.internet;

import com.connor.util.HttpUtils;

public class TestPeerDown {


    public static void main(String[] args) {

        //起来10个线程去请求http://159.75.46.9/peer
        String url = "http://159.75.46.9/peer";
        for (int i = 1; i <= 10; i++) {
            final String threadName = "thread" + i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int f = 1; f <= 10; f++) {
                        String threadRequestName = threadName + "- requestTime - :" + f;
                        String result = HttpUtils.URLGet(url, null, "utf-8");
                        System.out.println(threadRequestName + ":" + result);
                        sleepWell();
                    }
                }
            });
            thread.start();
            sleepWell();
        }
    }

    private static void sleepWell(){
        try {
            Thread.sleep(Long.parseLong("1000"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
