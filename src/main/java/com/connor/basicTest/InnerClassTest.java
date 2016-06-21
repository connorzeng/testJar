package com.connor.basicTest;

public class InnerClassTest {
	
	public static void main(String[] args) {
	    
	    TestI i = new TestI("zenggang", 1);
	    TestI f = i;
	    
	    System.out.println(TestI.getSingle().hashCode());
	    System.out.println(TestI.getSingle().hashCode());
	    System.out.println(TestI.getSingle().hashCode());
	    System.out.println(TestI.getSingle().hashCode());
	    System.out.println(TestI.getSingle().hashCode());
    }
}
class TestI{
    
    private static TestI single = new TestI("hello", 1); 
    private String name;
    private int age;
    
    public TestI(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public static TestI getSingle(){
        return single;
    }
}