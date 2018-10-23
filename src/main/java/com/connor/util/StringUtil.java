/**
 * Page.java
 * 
 * Copyright(C)2008 Founder Corporation. written by Founder Corp.
 */
package com.connor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [类名]<br>
 * Page<br>
 * [功能概要]<br>
 * <br>
 * <br>
 * [変更履歴]<br>
 * 2009-3-16 ver1.00 新建 zhaoxinsheng<br>
 * 
 * @author FOUNDER CORPORATION
 * @version 1.00
 */
public class StringUtil
{
    
    private static Random randGen = new Random();
    private static char[] numbersAndLetters =  ("0123456789abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
//    private static Object initLock = new Object();
    /**
  	 * 空字符串
  	 */
  	private static final String EMPTY_STR = "";
  	/**
  	 * 分隔符
  	 */
  	public static final String SEP = ",";
    public static final String SEP_BAR="|";
    public static final String SEP_COLON=":";
    /**
     * 判断是否为空，空返回true
     */
    public static boolean isEmpty(String str)
    {
    	return str == null || str.trim().length() < 1 || str.equals("null");
    }
    
    /**
     * 判断是否不为空，不空返回true
     */
    public static boolean isNotEmpty(String str)
    {
    
        return !isEmpty(str);
    }
    
    /**
     * 判断是否为空（全为空格、tab等字符也算空），空返回true。
     * @param str
     * @return
     */
    public static boolean isRealEmpty(String str) 
    {
    	return str == null || isEmpty(str.trim());
    }
    
    /**
     * 判断是否不为空（全为空格、tab等字符也算空），不空返回true
     * @param str
     * @return
     */
    public static boolean isNotRealEmpty(String str)
    {
    	return !isRealEmpty(str);
    }
    
    /**
     * 函数名称：arrayToStr 函数功能:把数组转换为字符串列表
     * 
     * @param array1 数组
     * @return 用","隔开的字符串列表
     */
    public static String arrayToStr(String[] array1)
    {
    
        String sResult = "";
        if (array1 == null)
        {
            return sResult;
        }
        for (int i = 0; i < array1.length; i++)
        {
            if ("".equals(sResult))
            {
                sResult = array1[i];
            }
            else
            {
                sResult += "," + array1[i];
            }
        }
        return sResult;
    }
    
    /**
     * 函数名称：arrayToStr 函数功能:把数组转换为字符串列表
     * 
     * @param array1 数组
     * @param splitChar 分隔符
     * @return 用splitChar隔开的字符串列表
     */
    public static String arrayToStr(String[] array1, String splitChar)
    {
    
        String sResult = "";
        if (array1 == null)
        {
            return sResult;
        }
        for (int i = 0; i < array1.length; i++)
        {
            if ("".equals(sResult))
            {
                sResult = array1[i];
            }
            else
            {
                sResult += splitChar + array1[i];
            }
        }
        return sResult;
    }
    
    /**
     * if aInStr is null,then return defaultStr
     * 
     * @param aInStr
     * @param defaultStr
     * @return
     */
    public static String getTrimString(Object aInObj, String defaultStr)
    {
    
        if (aInObj == null)
        {
            return defaultStr.trim();
        }
        else
        {
            return aInObj.toString().trim();
        }
    }
    
    /**
     * default string is blank.
     */
    public static String getTrimString(Object aInobj)
    {
    
        return getTrimString(aInobj, "");
    }
    
    public static String LPadString(String origin, int total, String pad)
    {
    
        String temp;
        StringBuffer tempBF = new StringBuffer();
        temp = (origin == null) ? "" : getTrimString(origin);
        
        for (int i = 0; i < total - getTrimString(origin).length(); i++)
        {
            tempBF.append(pad);
        }
        tempBF.append(temp);
        return tempBF.toString();
        
    }
    
    /**
     * 去除所有空格,包括中间的
     */
    public static String removeAllSpace(String str)
    {
    
        return str.replaceAll("\\s+", "");
    }
    
    /**
     * 折扣显示转换
     */
    public static String discountToString(String discount)
    {
    
        if (discount != null && !"".equals(discount))
        {
            String value = new Float(discount) * 100 + "";
            return value.substring(0, value.indexOf("."));
        }
        else
        {
            return "";
        }
    }
    
    /**
     * 将页面上折扣转换成存储格式（缩小100倍）
     */
    public static Float toDiscount(Float value)
    {
    
        return Float.parseFloat(value * 0.01 + "");
    }
    
    /**
     * String 转换 数组
     * 
     * @param str 字符串
     * @param sep 分割字符
     * @return
     */
    public static String[] str2Array(String str, String sep)
    {
    
        StringTokenizer token = null;
        String[] array = null;
        
        // check
        if (str == null || sep == null)
        {
            return null;
        }
        
        // get string array
        token = new StringTokenizer(str, sep);
        array = new String[token.countTokens()];
        for (int i = 0; token.hasMoreTokens(); i++)
        {
            array[i] = token.nextToken();
        }
        
        return array;
    }
    
    /**
     * String 转换为List
     * @param str
     * @param sep
     * @return
     */
    public static List<String> str2List(String str,String sep){
    	List<String> list=new ArrayList<String>();
    	if(isRealEmpty(str)) return list;
    	if(isRealEmpty(sep)){
    		sep=SEP;
    	}
    	list=Arrays.asList(str.split(sep));
    	return list;
    }
    public static String trim(String str)
    { // 去掉字符串2端的空白字符
    
        try
        {
            if (str == null)
            {
                return null;
            }
            str = str.trim();
            if (str == null)
            {
                return null;
            }
            return str;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return str;
    }
    
    /**
     * list<String> 转换SQLString<br>
     * 
     * @param inputList 输入的List型
     * @return String
     */
    public static String listToSQLString(List<String> inputList)
    {
    
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < inputList.size(); i++)
        {
            // output.append("'");
            if (StringUtil.isNotEmpty(inputList.get(i)))
            {
                output.append(inputList.get(i));
                // output.append("'");
                if (i != inputList.size() - 1)
                {
                    output.append(",");
                }
            }
        }
        return output.toString();
    }
    
    /***
     * 判断字符串是否数字
     * 
     * @param str
     * @return flag
     */
    public static boolean isNumeric(String str)
    {
    
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }
	/**
	 * 是否为数值
	 * @param str
	 * @return
	 */
    public static boolean isRealNumeric(String str){
		   
		   Pattern pattern = Pattern.compile("[+,-]?[0-9]+(\\.?[0-9]+)?"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   }else{
			   return true;
		   }
	}
    /**
     * 判断字符串是否是邮件
     * 
     * @param str
     * @return
     */
    public static boolean isEmail(String str)
    {
    
        Pattern pattern = Pattern
                .compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }
    
    /**
     * 判断是否是手机
     * 
     * @param str
     * @return
     */
    public static boolean isMobile(String str)
    {
    
        Pattern pattern = Pattern.compile("^0?(13|15|18|14)[0-9]{9}$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }
    
    /**
     * 判断用户名格式是否正确
     * 
     * @param str
     * @return
     */
    public static boolean isUserName(String str)
    {
    
        Pattern pattern = Pattern.compile("^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }
    
    /**
     * 获取字符串的长度（一个中文占两个字符长度）
     * 
     * @param str
     * @return
     */
    public static int getStringLength(String str)
    {
    
        if (isNotEmpty(str))
        {
            return str.replaceAll("[^\\x00-\\xff]", "**").length();
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * 判断密码格式是否正确
     * 
     * @param str
     * @return
     */
    public static boolean isPassword(String str)
    {
    
        Pattern pattern = Pattern.compile("^.*([\\W_a-zA-z0-9-])+.*$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args)
    {
    
        // System.out.println(StringUtil.removeAllSpace(" sss  sss  sss "));
        // String value = new Float(1.9) * 100 + "";
        //
        // System.out.println(isNumbericAll("01.00"));
        //
        // String prefix = "abc00000001".substring(0, "abc00000001".length() - 8);
        // System.out.println(prefix);
        // System.out.println("abc00000001".substring("abc00000001".length() - 8));
        // System.out.println(Integer.parseInt("abc00000001".substring("abc00000001".length() - 8)));
        //
        // System.out.println("  dddd  ".trim());
    	for (int i = 0; i < 6; i++) {
    		System.out.println(UUID.randomUUID());
    	}
        
        String usernmae = "<script/>alert(333)</script>";
        System.out.println(isPassword(usernmae));
    }
    
    /**
     * 得到单前系统的本地编码
     * 
     * @param encodedStr
     * @return
     */
    public static String getLocalStr(String encodedStr)
    {
    
        try
        {
            if (!(new String(encodedStr.getBytes("UTF-8"))).equals(encodedStr))
            {
                return new String(encodedStr.getBytes("UTF-8"));
            }
            else if (!(new String(encodedStr.getBytes("ISO-8859-1"))).equals(encodedStr))
            {
                return new String(encodedStr.getBytes("ISO-8859-1"));
            }
            return encodedStr;
        }
        catch (UnsupportedEncodingException e)
        {
            return encodedStr;
        }
    }
    
    /**
     * 
     * 输入 0 返回 Y（已删除） 输入 1 返回 N（未删除）
     * 
     * @param chars 1显示，0不显示
     * @return
     */
    public static String getYorN(String chars)
    {
    
        if (null == chars || "".equals(chars))
        {
            return "";
        }
        else
        {
            String res = "";
            if (chars.equals("1"))
                res = "N";
            if (chars.equals("0"))
                res = "Y";
            
            return res;
        }
    }
    
    /**
     * 返回所输长度的0字符串
     * 
     * @param length
     * @return
     */
    public static String getZeroString(Integer length)
    {
    
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            sb.append("0");
        }
        return sb.toString();
    }
    
    
    /**
     * 创建文件和文件目录
     * author guangyaohui
     * @param fileName
     * @return
     * boolean
     */
    public static boolean createFile(String fileName){
        try
        {
            File file = new File(fileName);
            if(!file.getParentFile().exists()){
                System.out.println("文件所在的目录不存在需要创建目录！");
                if(!file.getParentFile().mkdirs()){
                    System.out.println("创建目录失败!");
                    return false;
                }
            }
            if(file.exists() && file.canWrite()){
                System.out.println("文件已经存在，并且可写 ");
            } else {
            	file.delete();
            	System.out.println("已经存在的目标文件删除成功！");
            	System.out.println("文件开始创建 ");
                file.createNewFile();
                System.out.println("文件创建成功 ");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    /**
     * 
     * author guangyaohui
     * @param filePath
     * @param info
     */
    public static void writeFileToTheEnd(String filePath, String info) {
    	File file = new File(filePath);
        try
        {
        	createFile(filePath);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            StringBuffer sb = new StringBuffer();
            while (null != (inputLine = in.readLine()))
            {
            	if (sb.length() == 0) { 
            		sb.append(inputLine);
            	} else {
            		sb.append("\n"+inputLine);
            	}
            }
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            PrintWriter pw = new PrintWriter(out);
            if (sb.length() == 0) {
            	pw.print(info);
            } else {
            	pw.print(sb.toString()+"\n");
            	pw.print(info);
            }
            in.close();
            pw.close();
            out.close();
        }
        catch (IOException e)
        {
            
            e.printStackTrace();
        }
    }
    
    
    /**
     * 将
     * 
     * @param filePath
     * @param info
     */
    public static void writeFile(String filePath, String info)
    {
    
        File file = new File(filePath);
        try
        {
            file.createNewFile();
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            PrintWriter pw = new PrintWriter(out);
            pw.println(info);
            pw.close();
            out.close();
        }
        catch (IOException e)
        {
            
            e.printStackTrace();
        }
    }
    
    /**
     * 清空文件夹下的文件
     * 
     * @param path
     */
    public static void cleanDir(String path)
    {
    
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files)
        {
            f.delete();
        }
    }
    
    /**
     * 获取随即字符串
     * 
     * @param length 长度
     * @return
     */
    public static final String randomString(int length)
    {
    
        if (length < 1)
        {
            return null;
        }
//        if (randGen == null)
//        {
//            synchronized (initLock)
//            {
//                if (randGen == null)
//                {
//                    randGen = new Random();
//                    numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz_"
//                            + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
//                    // numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
//                }
//            }
//        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++)
        {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(numbersAndLetters.length)];
            // randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
    }
    
    public static String[] split(String str)
    {
    
        return split(str, ",");
    }
    
    /**
     * 字符串分割
     * 
     * @param str
     * @param delimiter
     * @return
     */
    public static String[] split(String str, String delimiter)
    {
    
        if (str == null)
        {
            return new String[0];
        }
        StringTokenizer st = new StringTokenizer(str, delimiter);
        List<String> tokens = new ArrayList<String>();
        while (st.hasMoreTokens())
        {
            String token = st.nextToken();
            token = token.trim();
            if (token.length() > 0)
            {
                tokens.add(token);
            }
        }
        return tokens.toArray(new String[tokens.size()]);
    }
    
    /**
     * 字符串转化成十六进制
     * 
     * @param bts
     * @return
     */
    public static String bytes2Hex(byte[] bts)
    {
    
        StringBuffer des = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1)
            {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

  
	
	/**
	 * 将为NULL的String设置为空串。
	 * 
	 * @param str
	 *            字符串
	 * @return String
	 */	
	public static String trimNull(String str) {
		return str == null ? EMPTY_STR : str;
	}
	
	/**
	 * 
	 *随即生成6位数 字或字母的组合
	 **/
	public static String randomSix(){
		char[] a={'1','2','3','4','5','6','7','8','9','a','b','c','d'};
		String b="";
		for(int i=0;i<6;i++){
			int index=(int)(Math.random()*(a.length));
			b=b+a[index];
		}
		return b;
	}
	
	/**
	 * 随机生成一个16进制的字符串
	 * @param len
	 * @return
	 */
	public static String randomHexString(int len) {
		char[] a={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		String b="";
		for(int i=0;i<len;i++){
			int index=(int)(Math.random()*(a.length));
			b=b+a[index];
		}
		return b;
	}
	
    /**
     * 将容易引起xss漏洞的字符替换成相对应的转义符或全角字符
     * 
     * @param s 需过滤的字符串
     * @return String 过滤后的字符串
     */
    public static String xssEncode(String s)
    {

        if (StringUtil.isRealEmpty(s))
        {
            return s;
        }
        int sLen = s.length();
        StringBuilder sb = new StringBuilder(sLen + 16);
        for (int i = 0; i < sLen; i++)
        {
            char c = s.charAt(i);
            switch (c)
            {
                case '>':
                    sb.append("&lt;");// 转义符&lt;
                    break;
                case '<':
                    sb.append("&gt;");// 转义符&gt;
                    break;
                case '\'':
                    sb.append("&prime;");// 转义符&prime;
                    break;
                case '′':
                    sb.append("&prime;");// 转义符&prime;
                    break;
                case '\"':
                    sb.append("&quot;");// 转义符&quot;
                    break;
                case '＂':
                    sb.append("&quot;");// 转义符&quot;
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }
    
    /**
	 * 判断制定对象Str是否为null
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isEmptyStr(Object obj) {
		if (obj == null){
			return true;
		} else if (isEmpty(obj.toString())){
			return true;
		} else if ("0".equals(obj.toString()) || "null".equals(obj.toString())){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断制定对象Str是否为null
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmptyStr(Object obj) {
		return !isEmptyStr(obj);
	}
	
}
