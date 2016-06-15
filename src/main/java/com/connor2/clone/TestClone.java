package com.connor2.clone;

/**
 * 测试clone
 *  JAVA 复制对象时为什么要用克隆clone（）而不用“=”的原因 ?
 *  -->二者指向的还是同一块内存,如下a1,a2,a3的示例, 这就是clone存在的原因
 *  
 * 
 * @author zenggang
 */
public class TestClone {
    
    public static void main(String[] args) throws CloneNotSupportedException {
        //1.克隆,"="于克隆的区别
        Address a1 = new Address();
        a1.setCountry("中国");
        a1.setProvince("湖南");
        Address a2 = (Address) a1.clone();
        Address a3 = a1;//其实是将一个引用复制过去了，二者指向的还是同一块内存
        a3.setCountry("中华民国");a2.setCountry("中国人民共和国");
        System.out.println(a1.equals(a2) + ":" + (a1 == a2));//a2的值改变不会改变a1的值
        System.out.println(a1.equals(a3) + ":" + (a1 == a3));//a3的值改变会改变a1的值
        System.out.print(a1);
        System.out.print(a2);
        System.out.println(a3);
        
        //2.浅克隆,深克隆的区别
        //2.1.浅克隆中u1.address u2.address内存地址是相同的(值相同),没有达到克隆的效果
        UserFlow u1 = new UserFlow();
        u1.setAddress(a1);
        u1.setUsername("曾罡");
        UserFlow u2 = (UserFlow) u1.clone();
        System.out.print(u1);
        System.out.println(u2);
        //2.2.深克隆中d1.address d2.address内存地址是不同(值相同),达到克隆的效果
        UserDeep d1 = new UserDeep();
        d1.setAddress(a1);
        d1.setUsername("曾罡");
        UserDeep d2 = (UserDeep) d1.clone();
        System.out.print(d1);
        System.out.println(d2);
    }
}

//浅克隆中address对象相当于"="号赋值
class UserFlow implements Cloneable{
    private String username;
    private Address address;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "User [username=" + username + ", address=" + address + "]";
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
}
//N层克隆
class UserDeep implements Cloneable{
    private String username;
    private Address address;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    @Override
    public String toString() {
        return "User [username=" + username + ", address=" + address + "]";
    }
    /**
     * 双层深克隆
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        UserDeep userDeep = (UserDeep) super.clone();
        if (userDeep.address != null){
            userDeep.address = (Address) userDeep.address.clone();
        }
        return userDeep;
    }
}

class Address implements Cloneable{
    private String country;
    private String province;
    
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    /*@Override
    public String toString() {
        return "Address [country=" + country + ", province=" + province + "]";
    }*/
}