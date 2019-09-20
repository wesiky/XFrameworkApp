package com.xframework.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

public class HttpUtil {
    private static String url;
    private static String content_type="text/xml; charset=utf-8";
    private static String namespace;
    private static String resquest_type="POST";
    private static String charset="UTF-8";

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        HttpUtil.url = url;
    }

    public static String getContentType() {
        return content_type;
    }

    public static void setContentType(String content_type) {
        HttpUtil.content_type = content_type;
    }

    public static String getNamespace() {
        return namespace;
    }

    public static void setNamespace(String namespace) {
        HttpUtil.namespace = namespace;
    }

    public static String getResquestType() {
        return resquest_type;
    }

    public static void setResquestType(String resquest_type) {
        HttpUtil.resquest_type = resquest_type;
    }

    public static String getCharset() {
        return charset;
    }

    public static void setCharset(String charset) {
        HttpUtil.charset = charset;
    }

    public static String sendSoapPost(String method_name, HashMap<String,String> map) {
        if(url.equals("") || namespace.equals(""))
        {
            return "HttpUtilHelper未初始化，请联系管理员";
        }
        HttpURLConnection httpConn = null;
        OutputStream out = null;
        String result = "";
        String dataXML = toDataXML(method_name,map);
        try {
            httpConn = (HttpURLConnection) new URL(url).openConnection();
            httpConn.setRequestProperty("Content-Type", content_type);
            if (null != method_name) {
                httpConn.setRequestProperty("SOAPAction", namespace+method_name);
            }
            httpConn.setRequestMethod(resquest_type);
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.connect();
            out = httpConn.getOutputStream(); // 获取输出流对象
            httpConn.getOutputStream().write(dataXML.getBytes(charset)); // 将要提交服务器的SOAP请求字符流写入输出流
            out.flush();
            out.close();
            int code = httpConn.getResponseCode(); // 用来获取服务器响应状态
            String tempString = null;
            StringBuffer sb1 = new StringBuffer();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpConn.getInputStream(),
                                charset));
                while ((tempString = reader.readLine()) != null) {
                    sb1.append(tempString);
                }
                if (null != reader) {
                    reader.close();
                }
            } else {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpConn.getErrorStream(),
                                charset));
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    sb1.append(tempString);
                }
                if (null != reader) {
                    reader.close();
                }
            }
            // 响应报文
            result = sb1.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return result;
    }

    /**
     * @param method_name 方法名
     * @param map 参数集合
     * @return 接口请求XML
     */
    private static String toDataXML(String method_name, HashMap<String,String> map) {
        StringBuilder xml = new StringBuilder("");
        xml.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"" + namespace + "\">")
                .append("<soapenv:Header/>")
                .append("<soapenv:Body>")
                .append("<web:" + method_name + ">");
        //填写参数
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = map.get(key);
            xml.append("<web:" + key + ">" + value + "</web:" + key + ">");
        }
        xml.append("</web:" + method_name + ">")
                .append("</soapenv:Body>")
                .append("</soapenv:Envelope>");
        return xml.toString();
    }

    public static String sendSoapPost(String url, String xml,String contentType, String soapAction) {
        String urlString = url;
        HttpURLConnection httpConn = null;
        OutputStream out = null;
        String result = "";
        try {
            httpConn = (HttpURLConnection) new URL(urlString).openConnection();
            httpConn.setRequestProperty("Content-Type", contentType);
            if (null != soapAction) {
                httpConn.setRequestProperty("SOAPAction", soapAction);
            }
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.connect();
            out = httpConn.getOutputStream(); // 获取输出流对象
            httpConn.getOutputStream().write(xml.getBytes("UTF-8")); // 将要提交服务器的SOAP请求字符流写入输出流
            out.flush();
            out.close();
            int code = httpConn.getResponseCode(); // 用来获取服务器响应状态
            String tempString = null;
            StringBuffer sb1 = new StringBuffer();
            if (code == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpConn.getInputStream(),
                                "UTF-8"));
                while ((tempString = reader.readLine()) != null) {
                    sb1.append(tempString);
                }
                if (null != reader) {
                    reader.close();
                }
            } else {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(httpConn.getErrorStream(),
                                "UTF-8"));
                // 一次读入一行，直到读入null为文件结束
                while ((tempString = reader.readLine()) != null) {
                    sb1.append(tempString);
                }
                if (null != reader) {
                    reader.close();
                }
            }
            // 响应报文
            result = sb1.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
