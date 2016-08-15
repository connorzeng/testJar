package com.connor.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.util.SleepUtils;

/**
 * 一.测试线程await/wait/sleep阻塞,响应中断 1.wait object.wait阻塞线程 2.sleep Thread.sleep阻塞线程 3.runing 运行状态 4.reentrantLock condition.await阻塞线程
 * 二.测试线程等待Lock时阻塞,响应中断 1.等待Synchronized 2.等待Lock 中断线程
 * 
 * @author zenggang
 */
public class InterruptTest {
    

    public static void main(String[] args) throws InterruptedException {

        Thread running = new Thread(new RunningRunner(), "runningRnner");// 运行状态
        Thread waitSyncLock = new Thread(new RunningRunner(), "waitSyncLock1");// 等待Synchronized
        
        Thread sleep = new Thread(new SleepRunner(), "sleepRunner");// Thread.sleep阻塞线程
        Thread wait = new Thread(new WaitingRunner(), "waitingRunner");// object.await阻塞线程
        
        Thread lock = new Thread(new LockRunner(), "lockRunner");// condition.await阻塞线程
        Thread waitLockLock = new Thread(new LockRunner(), "waitLockLock");// 等待Lock

        /*running.start();
        running.interrupt();//状态改变,线程继续执行
        System.out.println("main--running:" + running.isInterrupted() + " " + running.getState());//无阻塞线程可以中断
        waitSyncLock.start();
        waitSyncLock.interrupt();//synchronized:interrupted的状态改为ture,实际并没有中断线程
        System.out.println("main--waitSyncLock:" + waitSyncLock.isInterrupted() + " " + waitSyncLock.getState());*/
        
        //sleep.start();
        //sleep.interrupt();//报错线程继续执行
        //System.out.println("main--sleep:" + sleep.isInterrupted() + " " + sleep.getState());//报错
        
        wait.start();
        //wait.interrupt(); //报错线程继续执行
        System.out.println("main--wait111111:" + wait.isInterrupted() + " " + wait.getState());//报错
        /*synchronized (wait) {
			wait.notify();;
			System.out.println("main--开始执行了了");
		}*/ //不能唤醒线程
        
        
        /*lock.start();
        lock.interrupt(); //状态改变,线程继续执行
        System.out.println("main--lock:" + lock.isInterrupted() + " " + lock.getState());//报错
        waitLockLock.start();
        waitLockLock.interrupt();//Lock:interrupted的状态改为false,单实际已经中断了线程
        System.out.println("main--waitLockLock:" + waitLockLock.isInterrupted() +" " + waitLockLock.getState());//*/
        
        //死锁1
        /*final Object lock1 = new Object();
        final Object lock2 = new Object();
        Thread thread1 = new Thread() {
            public void run() {
                deathLock(lock1, lock2);
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                // 注意，这里在交换了一下位置
                deathLock(lock2, lock1);
            }
        };
        System.out.println("Starting thread...");
        thread1.start();
        thread2.start();
        Thread.sleep(3000);
        System.out.println("Interrupting thread...");
        thread1.interrupt();
        thread2.interrupt();
        Thread.sleep(3000);
        System.out.println("Stopping application...");*/
        
        
        //死锁2
        /*final ReentrantLock rlock1 = new ReentrantLock();
        final ReentrantLock rlock2 = new ReentrantLock();
        Thread threadr1 = new Thread() {
            public void run() {
                deathLock2(rlock1, rlock2);
            }
        };
        Thread threadr2 = new Thread() {
            public void run() {
                // 注意，这里在交换了一下位置
                deathLock2(rlock2, rlock1);
            }
        };
        System.out.println("Starting thread...");
        threadr1.start();
        threadr2.start();
        SleepUtils.killTime(3);
        System.out.println("Interrupting thread...");
        threadr1.interrupt();
        threadr2.interrupt();
        SleepUtils.killTime(3);
        System.out.println("Stopping application...");*/
        
    }
    
    
    static void deathLock(Object lock1, Object lock2) {
        try {
            synchronized (lock1) {
                Thread.sleep(10);// 不会在这里死掉
                synchronized (lock2) {// 会锁在这里，虽然阻塞了，但不会抛异常
                    System.out.println(Thread.currentThread());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    static void deathLock2(ReentrantLock lock1, ReentrantLock lock2) {
        try {
        	lock1.lock();
            Thread.sleep(10);// 不会在这里死掉
            lock2.lock();
            System.out.println(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }finally {
			lock1.unlock();
		}
    }
    
}

/**
 * wait阻塞线程不能响应Interrupt
 * 
 * @author zenggang
 */
class WaitingRunner implements Runnable {
    @Override
    public void run() {
    	synchronized (this) {// 一定要锁住这个线程的监视器,才能调用线程的wait方法
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    	System.out.println("notify connor");
    }
}

/**
 * sleep阻塞线程不能响应Interrupt
 * 
 * @author zenggang
 */
class SleepRunner implements Runnable {
    @Override
    public void run() {
        synchronized (this) {
            SleepUtils.second(10);
            System.out.println("SleepRunner wake up");
        }
    }
}

/**
 * 正在运行的线程可以响应Interrupt
 * 
 * @author zenggang
 */
class RunningRunner implements Runnable {
    @Override
    public void run() {
        synchronized (RunningRunner.class) {//必须要注意此处要锁住的对象.
            SleepUtils.killTime(5l);
            System.out.println("RunningRunner go on " + Thread.currentThread().getName());
        }
    	/*System.out.println("RunningRunner run begin:" + Thread.currentThread().getName());
    	SleepUtils.killTime(10l);
        System.out.println("RunningRunner run over:" + Thread.currentThread().getName());*/
    }
}

/**
 * reentrantLock线程可以响应Interrupt
 * @author zenggang
 */
class LockRunner implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cond = lock.newCondition();
    @Override
    public void run() {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            System.out.println("LockRunner interrupted " + Thread.currentThread().getName());
            return;
        }
        try {
            /*
             * cond.await();//cond.await();阻塞也不能响应Interrupt } catch (InterruptedException e) { e.printStackTrace();
             */
            SleepUtils.killTime(5);
            System.out.println("LockRunner wake up " + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
