package com.connor.jvm;

public class TestGC {


    public static void main(String[] args) throws InterruptedException {
    	
    	
    	for (int i=0;i<10;i++){
    		Thread d = new Thread(new Runnable() {
    			@Override
    			public void run() {
    				byte[][] allocation=new byte[11][];
    		        allocation[0] =new byte[1024 *1024*5];
    		        allocation[2] =new byte[1024 *1024*5];
    		        
    		        try {
						Thread.sleep(100000000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		});
    		d.start();
    		Thread.sleep(2000);
    	}
    	
    }
}
