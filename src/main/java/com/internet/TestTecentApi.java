package com.internet;

import com.util.HttpUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


public class TestTecentApi {

    private static final String SECRET_ID = "AKID054wiKpXIxgIjydKZdcS6as1VOC4YQq9";
    private static final String SECRET_KEY = "nVIzUs56zoY8lDUZjngT4Vkr0GTVLzRh";

    public void beforeTest(){
        System.out.println("before");
    }

    public void afterTest(){
        System.out.println("after");
    }

    public static void main(String[] args) {
        translatezh2En("你好吗");
    }




    /**
     * 中文翻译为英文
     * @return 原文翻译的英文
     */
    public static void translatezh2En(String sourceText){

        //1.计算签名
        //Tip:计算签名的params顺序要和URL请求的顺序一致,不然会报AuthFail
        String url = "https://tmt.api.qcloud.com/v2/index.php";
        Map<String, String> params = new TreeMap<>();
        params.put("Action","TextTranslate");
        params.put("Nonce","3253");
        params.put("Region","gz");
        params.put("SecretId",SECRET_ID);
        String timeStr = String.valueOf(new Date().getTime());
        params.put("Timestamp",timeStr.substring(0,timeStr.length()-3));
        params.put("source","zh");
        params.put("sourceText",sourceText);
        params.put("target","en");
        params.put("SignatureMethod","HmacSHA256");


        //注意请求方法GET要大写
        String srcStr = "GETtmt.api.qcloud.com/v2/index.php";
        srcStr = HttpUtils.getFullUrl(srcStr,params);
        String signature = sha256_HMAC(srcStr);
        //System.out.println("srcStr:" + srcStr);
        //System.out.println("signature = [" + signature + "]");
        params.put("Signature",signature);

        System.out.println("sourceText = " + sourceText);
        //要能使用eclipse中 ctlr+shift+i进行提前运算去的结果
        System.out.println(HttpUtils.URLGet(url,params,"utf-8"));
    }



    /**
     * sha256_HMAC加密
     *
     * @param message 消息
     * @return 加密后字符串
     */
    private static String sha256_HMAC(String message) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            hash = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;
    }

}
