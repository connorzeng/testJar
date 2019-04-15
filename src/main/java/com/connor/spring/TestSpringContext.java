package com.connor.spring;

import com.connor.spring.bean.Cat;
import com.connor.spring.bean.Dog;
import com.connor.util.StringUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

public class TestSpringContext {


    protected String a = "";

    public static void main(String[] args) {

        xmlFactory();
        //proFactory();
        //parentFactory();
        //prototypeFactory();
    }

    /**
     * 测试多例
     */
    private static void prototypeFactory() {

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(factory);
        xmlReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));


        //Dog dog = new Dog();  private 构造方法一样可以进行创建
        Dog dogprototype = (Dog) factory.getBean("dogprototype");
        Dog dogprototype2 = (Dog) factory.getBean("dogprototype");
        
        System.out.println(dogprototype.getName());

    }


    /**
     * 测试父子容器
     */
    private static void parentFactory() {

        //父容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("bean.properties"));


        //子容器
        /*XmlBeanFactory sonFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));*/
        DefaultListableBeanFactory sonFactory = new DefaultListableBeanFactory();
        sonFactory.setParentBeanFactory(factory);
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(sonFactory);
        xmlReader.loadBeanDefinitions(new ClassPathResource("beans.xml"));
        sonFactory.setParentBeanFactory(factory);


        boolean isSingleton = factory.isSingleton("dog");

        Dog dog1 = (Dog) factory.getBean("dog");
        Dog dog11 = (Dog) factory.getBean("dog");



        Dog dog2 = (Dog) sonFactory.getBean("dog");
        Cat cat = (Cat) sonFactory.getBean("cat");
        System.out.println("dog1.name:" + dog1.getName());
        System.out.println("dog2.name:" + dog2.getName());
        System.out.println("cat.name:" + cat.getName());
    }

    /**
     * 使用properteis加载beanDefinition
     */
    private static void proFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("bean.properties"));

        Dog dog = (Dog) factory.getBean("dog");
        System.out.println(" = " + dog.getName());
    }


    /**
     * 使用XML加载beanDefinition
     */
    private static void xmlFactory() {
        BeanFactory factory = new XmlBeanFactory(new ClassPathResource("beans.xml"));

        ((XmlBeanFactory) factory).getBeanDefinition("dog");

        // 通过Bean ID 获取
        Dog dog = (Dog) factory.getBean("dog");
        System.out.println("dog = " + dog);

        // 通过别名获取
        Dog dogAlia = (Dog) factory.getBean("dogname");
        System.out.println("dogAlia" + dogAlia);
    }


}
