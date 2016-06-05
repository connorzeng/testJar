package com.connor.thread;

public class TestWaiting {
	
	public static void main(String[] args) {
		
		Thread th = new Thread(new WaitingThread(),"");
		th.suspend();
		
		th.setDaemon(true);
		th.start();
		
	}
	
	static class WaitingThread implements Runnable{

		@Override
		public void run() {
			while(true){
				synchronized (WaitingThread.class) {
					try {
						WaitingThread.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
