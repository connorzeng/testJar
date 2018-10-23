package com.connor.high.innerClass;

/**
 * java闭包,回调
 * @author zenggang
 */
public class InnerClassClosure {  
    public static void main(String[] args) {  
        Callee1 c1 = new Callee1();  
        Callee2 c2 = new Callee2();  
        MyIncrement.f(c2);      // 输出:Other operation  
        /************************* 注意:这里应该输出的是, 
         *                           ------------------ 
         *                             Other operation 
         *                             1 
         *                           ------------------ 
         *                             而不是"Other operation",原因是这样:调用Callee2(是MyIncrement的子类)的increment()方法 
         */  
        System.out.println("测试用--------");  
        /*************使用回调类********************/  
        Caller caller1 = new Caller(c1);  
        Caller caller2 = new Caller(c2.getCallbackReference());//c2.getCallbackReference()返回一个Closure<内部类>的引用<>  
        caller1.go();           // 输出:1  
        caller1.go();           // 输出:2  
        caller2.go();           // 输出:Other operation  
                                // 输出:1           
        /********************** 正确输出:2  *************/  
        caller2.go();           // 输出:Other operation  
                                // 输出:2   
        /********************** 正确输出:3  *************/  
    }  
}  
/** 
 * Callbacks.<a href="http://lib.csdn.net/base/17" class='replace_word' title="Java EE知识库" target='_blank' style='color:#df3434; font-weight:bold;'>Java</a> 
 * 通过内部类提供闭包的功能是优良的解决方案,它比指针更灵活,更安全. 
 * --<Thingking in Java>第四版 Page 206 
 */  
  
/** ********************************************************************* */  
interface Incrementable {  
    void increment();  
}  
  
/** ********************************************************************* */  
// Very simple to just implement the interface:  
class Callee1 implements Incrementable {  
    private int i = 0;  
  
    public void increment() {  
        i++;  
//      System.out.println(i);  
        System.out.println("[Callee1] --> " + i);  
    }  
}  
  
/** ********************************************************************* */  
class MyIncrement {  
    public void increment() {  
//      System.out.println("Other operation");  
        System.out.println("[MyIncrement] --> " + "Other operation");  
    }  
  
    static void f(MyIncrement mi) {  
        mi.increment();  
    }// 自己调用自己  
}  
  
/** ********************************************************************* */  
// If your class must implement increment() in  
// some other way, you must use an inner class:  
// 认真理解本类  
class Callee2 extends MyIncrement {//继承MyIncrement类  
    private int i = 0;  
  
    public void increment() {// 覆盖父类的方法  
        super.increment();  
        i++;  
//      System.out.println(i);  
        System.out.println("[Callee2] --> " + i);  
    }  
  
    // 内部类Closure提供了一个返回外围类Callee2的"钩子"(hook)-而且是一个安全的钩子  
    private class Closure implements Incrementable {// 实现Incrementable接口  
        public void increment() {
            // Specify outer-class method, otherwise  
            // you'd get an infinite recursion:  
            Callee2.this.increment();// 调用外围类的increment()方法  
        }  
    }  
  
    Incrementable getCallbackReference() {  
        return new Closure();  
    }  
}  
  
/** ********************************************************************* */  
class Caller {// 回调类  
    private Incrementable callbackReference;  
  
    Caller(Incrementable cbh) {// 构造方法  
        callbackReference = cbh;  
    }  
  
    void go() {  
        callbackReference.increment();  
    }  
}  
