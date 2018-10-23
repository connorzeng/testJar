package com.connor.basic;

import org.junit.Assert;
import org.junit.Test;

/**
 * 正则表达式
 * 
 * @author ZENGGANG918
 *
 */
public class RegExSample {
    
    @Test
    public void testSample(){
        
        String dateRegEx = "[0-9]{4}-[0,1]?[0-9]{1}-[0-9]{2}";
        
        
        Assert.assertTrue("2018-26-12".matches(dateRegEx));
        
    }
    
    
}
