package com.connor.basic.basicTest;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * 泛型:通过<T>导入泛型
 * 1.泛型接口/泛型类
 * 2.泛型方法
 * 
 * a.T-具体类型
 * b.?-不定类型
 * c.Object-不配置类型
 * d.? extends Integer-配置泛型上下界
 * @author zenggang
 */
public class GenericTest {
    
    public static void main(String[] args) {
        //1.泛型只是编译检查有用,在编译器会对泛型进行擦除
        try {
            List<Integer> list = new ArrayList<Integer>();//Integer类型
            list.add(1);
            //通过反射调用list的add方法
            list.getClass().getMethod("add", Object.class).invoke(list, "abc");//String类型
            for(int i = 0;i<list.size();i++){
                System.out.println(list.get(i));
            }
        } catch (IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }
        //2.泛型方法
        ConnorGeneric3<? extends Serializable,String,Integer> ldd = new ConnorGeneric3<String,String,Integer>();
        List<? extends Serializable> list = new ArrayList<String>();
        
        //3.通配符捕获
        List<?> lists = new ArrayList<String>();
        addList(lists, "dd");//调用捕获方法
        for (Object obj : lists){
            System.out.println(obj.toString());
        }
    }
    public static <V> void addList(List list ,V v){
        list.add(v);
    }
}
/**
 * 接口泛型/类泛型
 */
interface Generic<T>{
    public T getClassName();
}
abstract class ConnorGeneric<F> implements Generic<String>{//接口/类泛型  需要指定下来或者继承下去
}
class ConnorGeneric2<F> extends ConnorGeneric<F>{
    @Override
    public String getClassName() {
        return null;
    }
    public F getF(){
        return null;
    }
}
/**
 * 泛型方法
 */
class ConnorGeneric3<T extends Serializable,Y,Z>{
    public T getT(){
        return null;
    }
    public <F> void wildcard(List<F> list) {//设置泛型上下界
        //list.add(1);//编译错误 
    }  
    public <F> void wildcard(List<? extends Serializable> list,List<? super String> list2) {//设置泛型上下界
        //list.add(1);//编译错误 
    } 
}
class Info<T extends Number>{ // 指定上限，只能是数字类型  
    private T var ;     // 此类型由外部决定  
    public T getVar(){  
        return this.var ;     
    }  
    public void setVar(T var){  
        this.var = var ;  
    }  
    public String toString(){       // 覆写Object类中的toString()方法  
        return this.var.toString() ;      
    }  
};  
class GenericsDemo{  
    public static void main(String args[]){  
        Info<Integer> i = fun(30) ;  
        System.out.println(i.getVar()) ;  
    }  
    public static <T extends Number> Info<T> fun(T param){  
        Info<T> temp = new Info<T>() ;      // 根据传入的数据类型实例化Info  
        temp.setVar(param) ;        // 将传递的内容设置到Info对象的var属性之中  
        return temp ;   // 返回实例化对象  
    }  
};
/**
 * 通配符捕获
 */
interface Box<T> {
    public T get();
    public void put(T element);
}
class TestBox{
    public void unbox(Box<?> box) {
        System.out.println(box.get());
    }
    public void rebox(Box<?> box) {
        //The method put(capture#3-of ?) in the type Box<capture#3-of ?> is not applicable for the arguments (capture#4-of ?)
        //box.put(box.get());//报错,必须使用捕获通配符
        reboxHelper(box);
    }
    private<V> void reboxHelper(Box<V> box) {
        box.put(box.get());
    }
}



