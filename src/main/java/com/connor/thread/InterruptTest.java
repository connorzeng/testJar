package com.connor.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.connor.tool.SleepUtils;

/**
 * 一.测试线程await/wait/sleep阻塞,响应中断 1.wait object.await阻塞线程 2.sleep Thread.sleep阻塞线程 3.runing 运行状态 4.reentrantLock condition.await阻塞线程
 * 二.测试线程等待Lock时阻塞,响应中断 1.等待Synchronized 2.等待Lock 中断线程
 * 
 * @author zenggang
 */
public class InterruptTest {
    
    public static synchronized void killTime(){
        SleepUtils.killTime(10l);
    }

    public static void main(String[] args) {

        Thread running = new Thread(new RunningRunner(), "runningRnner");// 运行状态
        Thread sleep = new Thread(new SleepRunner(), "sleepRunner");// Thread.sleep阻塞线程
        Thread wait = new Thread(new WaitingRunner(), "waitingRunner");// object.await阻塞线程
        Thread lock = new Thread(new LockRunner(), "lockRunner");// condition.await阻塞线程
        Thread waitSyncLock = new Thread(new RunningRunner(), "waitSyncLock1");// 等待Synchronized
        Thread waitLockLock = new Thread(new LockRunner(), "waitLockLock");// 等待Lock
        running.start();
        sleep.start();
        wait.start();
        lock.start();
        
        SleepUtils.second(1);
        
        waitSyncLock.start();
        waitLockLock.start();
        

        // running.interrupt();
        // sleep.interrupt();
        // wait.interrupt();
        // lock.interrupt();
        waitSyncLock.interrupt();//synchronized:interrupted的状态改为ture,实际并没有中断线程
        waitLockLock.interrupt();//Lock:interrupted的状态改为false,单实际已经中断了线程

        System.out.println("running:" + running.isInterrupted() + " " + running.getState());//无阻塞线程可以中断
        System.out.println("sleep:" + sleep.isInterrupted() + " " + sleep.getState());//报错
        System.out.println("wait:" + wait.isInterrupted() + " " + wait.getState());//报错
        System.out.println("lock:" + lock.isInterrupted() + " " + lock.getState());//报错
        System.out.println("waitSyncLock1:" + waitSyncLock.isInterrupted() + " " + waitSyncLock.getState());//
        System.out.println("waitLockLock:" + waitLockLock.isInterrupted() +" " + waitLockLock.getState());//
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
                System.out.println("notify");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        /*synchronized (this) {//必须要注意此处要锁住的对象.
            SleepUtils.killTime(10l);
        }*/
        InterruptTest.killTime();
        System.out.println("RunningRunner run over:" + Thread.currentThread().getName());
    }
}

/**
 * reentrantLock线程可以响应Interrupt
 * 
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
            e.printStackTrace();
        }
        try {
            /*
             * cond.await();//cond.await();阻塞也不能响应Interrupt } catch (InterruptedException e) { e.printStackTrace();
             */
            SleepUtils.second(10);
            System.out.println("LockRunner wake up" + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }
}
