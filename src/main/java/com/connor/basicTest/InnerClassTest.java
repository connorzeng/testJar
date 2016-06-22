package com.connor.basicTest;

import com.connor.basicTest.InnerInterface.InterfaceInnerClass;
import com.connor.basicTest.TestInner.InnerClassImpl;
import com.connor.basicTest.TestInner.InnerClassStaticImpl;
/**
 * 内部类测试
 * @author zenggang
 */
public class InnerClassTest {
    
    public static void main(String[] args) {
        InterfaceInnerClass InterfaceInnerClass = new InterfaceInnerClass();//直接实例化接口内部类(public static)
        InnerClassStaticImpl innerClassStaticImpl = new InnerClassStaticImpl();//直接实例化嵌套内部类(public static)
        
        TestInner inner = new TestInner();
        InnerClassImpl innerClassImpl = inner.new InnerClassImpl();//普通内部类必须接触对象进行实例化
    }
}

class TestInner{
    
    private static String NAME = "zenggang";
    private String name = "zenggang";
    private int age = 1;
    private static int AGE = 1;
    //静态块里面的内部类,不能直接实例化
    static {
        class InnerStaticContent{
            public String getName(){
                return NAME; 
            }
        }
    }
    //块里面的内部类,不能直接实例化
    {
        class InnerContent{
            public String getName(){
                return name; 
            }
        }
    }
    //普通内部类,不能直接实例化
    public class InnerClassImpl implements InnerClass{
        @Override
        public int getInt() {
            return age;
        }
    }
    //静态内部类,可以直接实例化
    public static class InnerClassStaticImpl implements InnerClass{
        @Override
        public int getInt() {
            return AGE;
        }
    }
    //在方法体内的内部类
    public InnerClass getInt(){
        int a = 1;
        class InnerClassImpl implements InnerClass {
            //private static int ab = 1;//普通内部类不能声明static变量
            private int a;
            
            public InnerClassImpl(int a){
                this.a = a;
            }
            @Override
            public int getInt() {
                return age;
            }
        }
        return new InnerClassImpl(a);
    }
    
    //在方法体内的匿名内部类
    //1.匿名内部类没有构造器
    public InnerClass getInnerClass(){
        final int a = 1;//Cannot refer to the non-final local variable a defined in an enclosing scope
        return new InnerClass() {
            @Override
            public int getInt() {
                return a;
            }
        };
    }
}
//接口内部类默认未public static
interface InnerInterface{
    static class InterfaceInnerClass implements InnerClass{
        @Override
        public int getInt() {
            return 0;
        }
    }
}
interface InnerClass{
    int getInt();
}

/**
 * 利用内部类实现多重继承
 */
class Father{
    public void beStrong(){
    }
}
class Mother{
    public void beKind(){
    }
}
class Son{
    class Father_x extends Father{
    }
    class Mother_x extends Mother{
    }
    public void beStrong(){
        new Father_x().beStrong();
    }
    public void beKind(){
        new Mother_x().beKind();
    }
}

/**
 * 闭包,回调
 */
interface House{
    public void live();
}
class MyHouse implements House{
    @Override
    public void live() {
        System.out.println("穷逼的房子");
    }
}
class OtherPeopleHouse{
    public void live(){
        System.out.println("我是土豪,我的房子就是介么豪华.");
    }
    class AgentHouse implements House{
        @Override
        public void live() {
            System.out.println("我是代理人,我来告诉你土豪的房子有多么豪华");
            OtherPeopleHouse.this.live();
        }
    }
    public House getHouse(){
        return new AgentHouse();
    }
}
