package com.xframework.util;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class WebServiceUtil {

    private static int timeout = 90*1000;//超时时间
    private static String wsdl_url = "";//url地址
    private static String name_sapce = "";//命名空间
    private static int soap_version = SoapEnvelope.VER12;//soap版本



    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        WebServiceUtil.timeout = timeout;
    }

    public static String getWsdl_url() {
        return wsdl_url;
    }

    public static void setWsdl_url(String wsdl_url) {
        WebServiceUtil.wsdl_url = wsdl_url;
    }

    public static String getName_sapce() {
        return name_sapce;
    }

    public static void setName_sapce(String name_sapce) {
        WebServiceUtil.name_sapce = name_sapce;
    }
    public static int getSoap_version() {
        return soap_version;
    }

    public static void setSoap_version(int soap_version) {
        WebServiceUtil.soap_version = soap_version;
    }
    /* 调用webService */
    public static String callToWebService(String method_name, HashMap<String,String> map) {
        String result = "";
        if(wsdl_url.equals("")||name_sapce.equals(""))
        {
            result = "调用失败，WebService未初始化。";
            return result;
        }
        HttpTransportSE httpTransportSE = new HttpTransportSE(wsdl_url,timeout);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(soap_version);
        SoapObject request = new SoapObject(name_sapce, method_name);
        //填写参数
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = map.get(key);
            request.addProperty(key,value);
        }

        envelope.bodyOut = request;
        envelope.dotNet = true;//若接口为.net搭建，需要设置为true
        try {
            httpTransportSE.call(method_name, envelope);//调用

            //获取结果
            if (envelope.getResponse() != null) {
                result = envelope.getResponse().toString().trim();
            }
            else {

                SoapObject object = (SoapObject) envelope.bodyIn;

                result = object.getProperty(0).toString();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "接口调用出错，请联系管理员";
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            result = "接口调用出错，请联系管理员";
        }
        return result;
    }

    /* 调用webService */
    public static String callToWebService(String wsdl_url, String name_sapce, String method_name, HashMap<String,String> map) {
        String result = "";
        HttpTransportSE httpTransportSE = new HttpTransportSE(wsdl_url,timeout);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(soap_version);
        SoapObject request = new SoapObject(name_sapce, method_name);
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = (String) iterator.next();
            String value = map.get(key);
            request.addProperty(key,value);
        }
        envelope.bodyOut = request;
        envelope.dotNet = true;
        try {
            httpTransportSE.call(method_name, envelope);//调用
            if (envelope.getResponse() != null) {
                result = envelope.getResponse().toString().trim();
            }
            else {

                SoapObject object = (SoapObject) envelope.bodyIn;

                result = object.getProperty(0).toString();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return result;
    }
}
