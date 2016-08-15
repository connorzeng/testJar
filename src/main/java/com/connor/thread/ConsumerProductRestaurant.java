package com.connor.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * notify() wait() 实现生产者消费者
 * 
 * @author connor_zeng
 *
 */
public class ConsumerProductRestaurant {

	class Meal {
		private int orderNum = 0;

		public Meal(int orderNum) {
			this.orderNum = orderNum;
		}

		@Override
		public String toString() {
			return "Meal orderNum :" + orderNum;
		}
	}

	class Chief implements Runnable {

		private Restaurant restaurant;

		public Chief(Restaurant restaurant) {
			this.restaurant = restaurant;
		}

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				synchronized (this) {//Chief.class Exception in thread "pool-1-thread-1" java.lang.IllegalMonitorStateException
					while (restaurant.meal != null) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				synchronized (restaurant.eater) {
				//synchronized (Eater.class) {  //不能所住Eeter.class
					if (restaurant.mealCount >= 10) {
						//restaurant.exec.shutdown(); //等当前线程结束后关系关闭线程池,不会发出Interrutped请求
						restaurant.exec.shutdownNow();// 打断当前线程,立马关闭,发出Interruted请求
					} else {
						System.out.println("  我这是做的第" + Integer.valueOf(restaurant.mealCount+1) + "菜");
						restaurant.meal = new Meal(restaurant.mealCount+1);
						restaurant.mealCount++;
						restaurant.eater.notifyAll();
					}
				}
			}
			System.out.println("OUT ORDER CHIEF");
		}
	}

	class Eater implements Runnable {
		private Restaurant restaurant;

		public Eater(Restaurant restaurant) {
			this.restaurant = restaurant;
		}

		/**
		 * 吃东西
		 */
		@Override
		public void run() {
			try {
				while (!Thread.interrupted()) {
					synchronized (this) {
						while (restaurant.meal == null) {
							System.out.println("我要吃东西,但是要等 id:");
							wait();
						}
					}
					synchronized (restaurant.chief) {
						System.out.println("我吃了东西了:"
								+ + restaurant.meal.orderNum);
						restaurant.meal = null;
						restaurant.chief.notifyAll();
					}
				}
			} catch (InterruptedException e) {
				System.out.println("out order Eater");
			}
		}
	}

	class Restaurant {
		ExecutorService exec = Executors.newCachedThreadPool();
		private int mealCount = 0;
		private Meal meal = null;

		private Chief chief = new Chief(this);
		private Eater eater = new Eater(this);

		public Restaurant() {
			exec.execute(chief);
			exec.execute(eater);
		}
	}

	public void newRestaurant() {
		new Restaurant();
	}

	public static void main(String[] args) {
		ConsumerProductRestaurant s = new ConsumerProductRestaurant();
		s.newRestaurant();
	}
}
