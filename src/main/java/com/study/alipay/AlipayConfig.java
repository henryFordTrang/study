package com.study.alipay;

import java.io.FileWriter;
import java.io.IOException;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016080800194569";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCE+sX+6iUTqBAHjq25xmIE5gfk7OcFe6Jx3B2TiWcRqFIvfcLNJZ7laQilt3eBL/WY1Op9c5E1vgMOLL2NLa0mhf3pDKUf0WYSt1vRqBhbl136bkd5z998ouYRXHm26q1Q3zWowcSlZ0RKGVCQri6u9mwSHFwh9ftyA6LkSCu6HWfJpVKUZpB7cE23RRZuQBpmqpis1XO3M2j1qI8t1IbdKJuurO3B4hmRL4v1FwigFNPY67s6afUCs9jTWGh5dQlhdVb7PefKEBDloFHzzcWvrmrZvxf8p+vYqO9SZpR0q24GGfSqTAWeOSIC+rPtSf5TYLM+gpcfOHSGn9aAPJQZAgMBAAECggEAVoQI+RZA1Q8Mjr4nDKyi7Ojzzd7LVuffAhDhNTG52uUVw0wgxvNo83xoYdiBaY1YTfyIKuJbYQmkVz6IfCdxdZTLYyoU7CSCMj8U/8xLPJu4SswguIGjtyUMZdMyZO09oVX3RTn7xFjxNvxNTDPmr3InUYkd8AJnewAepELIICc8NLwnpyh6agZirwllMbi66763L1xllseHDVozkYkW+P0qPpBQtxurgPt+BnWz+odTuMfdXCPmnIYMiRJVFVsooymanzcHPBj6KJ4YAYCCuRXxReWMZVRvXC8tZY7spRCTyXnvOcpDmeuYg3jqy+aCtDnh8uIpxmDah3x5kwv0AQKBgQDLo7g8TntABQ0GRHTivvkLJb7JPqjWtUhZMYfKfQ7YYp0EYiST0SQXxJ+kHv9Oesyh++5IfCM+eQnY9JpzE32xE3WsIZigXmakBrh936yd2uJ2cJw26HcUNDeJAFGcgpATnBXdXz28jqYVj+hIwF4+9uaRZTRCVW6hSGwJoYJl2QKBgQCnK/bJhYyV0WqKKzVts3HgYh0V9x3l0T4ojrh5BGqb/F0ZsjnAN3GDD9+VyRPbn2uUv8C8DXAWnJvcETsR2OftNv1jG+uVYdaDl6kCrzLXlXAptBXuyWxvKx9KgWpw1L1iixNry61iSecEuM5kLo7irz7DZpqvwhNPINAE1Jt4QQKBgFJKB2H/SNjW8attsFoMV/EEQLNr6ahRuzmnxTqzDHOubcreqe89nTspjmUwzXR2NQjkGCccWtWc7bjJJzyHTMxok62KKfXsD8gKmzTqVQO49rhrywR5Ld1xOFvZUCFRtUznTwKUwQrYDg86huAXND53YTax0ctYYIs2C8pchKgJAoGBAJ4KNRwdEmRMf4EyCie0nnK3rrGmVnB+2Vm0W3elEjh3Y3i4glpS0BBMgF51oz1cwHL2uPnPZmkqEzsAWyyrN8+NzgzVR2mI/iiXxyMPiGxBDbGPW7DMYaPTS5i/MmaK6KPxwSGGg1rJbQqMwCn7zf9GF3rZVGHWgzSqW55FdNlBAoGBAJD+UYRrI6r6JoGIH1EWioH2VCnlYFZXSCOOnyNyy8wzTqGxCj/JYupYjc+iZl973dcWPxfIjy+rBs/Y3IZaFcOg9NMoWXeFS2Gua+b8qD5nJu9huFLT1f16sRNYOKY4Gtyqg/EJXXffcZS+WrWUEIREcxoyT9xIhqIZaW6eFmGB";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhPrF/uolE6gQB46tucZiBOYH5OznBXuicdwdk4lnEahSL33CzSWe5WkIpbd3gS/1mNTqfXORNb4DDiy9jS2tJoX96QylH9FmErdb0agYW5dd+m5Hec/ffKLmEVx5tuqtUN81qMHEpWdEShlQkK4urvZsEhxcIfX7cgOi5Egruh1nyaVSlGaQe3BNt0UWbkAaZqqYrNVztzNo9aiPLdSG3SibrqztweIZkS+L9RcIoBTT2Ou7Omn1ArPY01hoeXUJYXVW+z3nyhAQ5aBR883Fr65q2b8X/Kfr2KjvUmaUdKtuBhn0qkwFnjkiAvqz7Un+U2CzPoKXHzh0hp/WgDyUGQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://www.baidu.com/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.baidu.com/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

