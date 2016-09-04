package com.connor.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FunnyTest {
    
    public static void main(String[] args) {
        Integer[] a1 = {1,8};
        Integer[] a2 = {2,7};
        Integer[] a3 = {3,6};
        Integer[] a4 = {4,5};
        List<Integer[]> a = new ArrayList<Integer[]>();
        a.add(a1);a.add(a2);a.add(a3);a.add(a4);
        
        Integer[] b1 = {1,6};
        Integer[] b2 = {2,5};
        Integer[] b3 = {3,4};
        List<Integer[]> b = new ArrayList<Integer[]>();
        b.add(b1);b.add(b2);b.add(b3);
        
        Integer[] c1 = {1,2};
        Integer[] c2 = {2,3};
        Integer[] c3 = {3,4};
        Integer[] c4 = {4,5};
        Integer[] c5 = {5,6};
        Integer[] c6 = {6,7};
        Integer[] c7 = {7,8};
        List<Integer[]> c = new ArrayList<Integer[]>();
        c.add(c1);c.add(c2);c.add(c3);c.add(c4);c.add(c5);c.add(c6);c.add(c7);
        
        Integer[] d1 = {1,3};
        Integer[] d2 = {2,4};
        Integer[] d3 = {3,5};
        Integer[] d4 = {4,6};
        Integer[] d5 = {5,7};
        Integer[] d6 = {6,8};
        List<Integer[]> d = new ArrayList<Integer[]>();
        d.add(d1);d.add(d2);d.add(d3);d.add(d4);d.add(d5);d.add(d6);
        
        for (Integer[] as: a){
            for (Integer[] bs: b){
                for (Integer[] cs: c){
                    for (Integer[] ds: d){
                        Set<Integer> set = new HashSet<Integer>();
                        set.addAll(Arrays.asList(as));
                        set.addAll(Arrays.asList(bs));
                        set.addAll(Arrays.asList(cs));
                        set.addAll(Arrays.asList(ds));
                        if (set.size() >= 7){
                            System.out.print(as[0] +":"+ as[1] +"/");
                            System.out.print(bs[0]  +":"+ bs[1]+"/");
                            System.out.print(cs[0]  +":"+ cs[1]+"/");
                            System.out.print(ds[0]  +":"+ ds[1]+"/");
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
}
