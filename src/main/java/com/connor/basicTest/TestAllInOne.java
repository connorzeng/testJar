package com.connor.basicTest;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TestAllInOne {
    
    public static void main(String[] args) {
        System.out.println(TestStringTokenizer.getTokens("hello beautiful world"));
        
    }
}

class TestStringTokenizer{
    
    public static String[] getTokens(String str,String delim){
        StringTokenizer strTokenizer = new StringTokenizer(str, delim);
        List<String> list = new ArrayList<String>();
        while(strTokenizer.hasMoreTokens()){
            list.add(strTokenizer.nextToken());
        }
        return (String[]) list.toArray();
    }
    public static String[] getTokens(String str){
        StringTokenizer strTokenizer = new StringTokenizer(str);
        List<String> list = new ArrayList<String>();
        while(strTokenizer.hasMoreTokens()){
            list.add(strTokenizer.nextToken());
        }
        return list.toArray(new String[list.size()]);
    }
    
}
