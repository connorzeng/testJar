package com.connor.basicTest;

public class InnerClassTest {
    
    public static void main(String[] args) {
        
    }
}

class TestInner{
    /**
     * 在方法体内的内部类
     */
    public InnerClass getInt(){
        final int a = 1;
        class InnerClassImpl implements InnerClass {
            //private static int ab = 1;//普通内部类不能声明static变量
            public InnerClassImpl(){
            }
            
            @Override
            public int getInt() {
                return a;
            }
        }
        return new InnerClassImpl();
    }
    
    /**
     * 在方法体内的匿名内部类
     * 1.匿名内部类没有构造器
     */
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

interface InnerClass{
    int getInt();
}

