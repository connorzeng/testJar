package com.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public class BeanUtil extends BeanUtils{
    
    /**
     * 使用流进行bean克隆
     * @param src
     * @return
     * @throws RuntimeException
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneTo(T src) throws RuntimeException {

        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        T dist = null;

        try {
            out = new ObjectOutputStream(memoryBuffer);
            out.writeObject(src);
            out.flush();
            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
            dist = (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null)
            try {
                out.close();
                out = null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (in != null)
            try {
                in.close();
                in = null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return dist;
    }
    
    /**
     * 使用Spring BeanUtil 作为基类进行 熟悉反射copy
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<? extends Object> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {

            if ((targetPd.getWriteMethod() != null)) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if ((sourcePd != null) && (sourcePd.getReadMethod() != null))
                    try {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!(Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())))
                            readMethod.setAccessible(true);

                        Object value = readMethod.invoke(source, new Object[0]);
                        if (value != null) {
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!(Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())))
                                writeMethod.setAccessible(true);

                            writeMethod.invoke(target, new Object[] { value });
                        }
                    } catch (Throwable ex) {
                        throw new FatalBeanException("Could not copy properties from source to target", ex);
                    }
            }
        }

    }

    
    @SuppressWarnings("unchecked")
    public static <T> List<T> getModelList(List<T> list, T model) {
        List<T> resultList = new ArrayList<T>();
        try {
            for (T t : list) {
                T obj = (T) model.getClass().newInstance();
                copyProperties(t, obj);
                copyProperties(model, obj);
                resultList.add(obj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
