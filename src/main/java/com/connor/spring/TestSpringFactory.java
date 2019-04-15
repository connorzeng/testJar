package com.connor.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class TestSpringFactory {

    public static void main(String[] args) {

        //testInit
        testInit();

    }

    /**
     * 测试init方法
     */
    private static void testInit() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("initBean.xml"));

        factory.getBean("connorBean");
        factory.getBean("connorBean");
    }

}
