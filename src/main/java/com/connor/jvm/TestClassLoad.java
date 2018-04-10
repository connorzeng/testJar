package com.connor.jvm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class TestClassLoad {
	static{
		System.out.println("我被加载了");
	}
	
	public static void main(String[] args) {
		//Dog dog = new Dog();//类加载
		//Dog[] dogs = new Dog[2];//不会被加载
		//System.out.println(Dog.FINAL_VALUE);//引用常量不会被加载
		//System.out.println(Dog.VALUE);//类加载
		//Dog.finalMethod();//类加载
		
		/**
		 * 测试自定义类加载器
		 */
		ClassLoader myLoader1 = new MyLoader();
		ClassLoader myLoader2 = new MyLoader();
		
		try {
			Animal animal = new Animal();
			animal.finalMethod("animal1");
			
			Class clz = myLoader1.loadClass("com.connor.jvm.Animal");
			Object animal1 = clz.newInstance();
			//Method finalMethod = clz.getMethod("finalMethod", Object.class);
			Method[] methods = clz.getMethods();
			for (Method finalMethod: methods){
				if (finalMethod.getName().equals("finalMethod")){
					finalMethod.invoke(animal1, "animal2");
				}
			}
			
			/*Animal animal =new Animal();
			Method finalMethod = animal.getClass().getMethod("finalMethod", Object.class);
			finalMethod.invoke(animal, null);*/
			
			Object obj = myLoader1.loadClass("com.connor.jvm.TestClassLoad").newInstance();
			System.out.println(obj.getClass());
			System.out.println(obj instanceof TestClassLoad);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (myLoader1 != null) {
            System.out.println(myLoader1.toString()); 
            myLoader1 = myLoader1.getParent(); 
        }
		
		/**
		 * 测试AppClassLoader
		 */
		ClassLoader loader = TestClassLoad.class.getClassLoader(); 
        while (loader != null) { 
            System.out.println(loader.toString()); 
            loader = loader.getParent(); 
        }
	}
}


class MyLoader extends ClassLoader{
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		try {
			String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
			InputStream is = getClass().getResourceAsStream(fileName);
			if (is == null){
				return super.loadClass(name);
			}
			byte[] b = new byte[is.available()];
			is.read(b);
			return defineClass(name, b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

