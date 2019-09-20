package com.xframework.backgroundTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;

import org.ksoap2.serialization.SoapSerializationEnvelope;

import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;


public class WebServiceHelper {
    private WebServiceDelegate delegate;

    /* 调用webService */
    public static String callToWebService(String wsdl_url, String name_sapce, String method_name, HashMap<String,String> map) {
        String result = "";
        HttpTransportSE httpTransportSE = new HttpTransportSE(wsdl_url,90*1000);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
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

    public WebServiceDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(WebServiceDelegate delegate) {
        this.delegate = delegate;
    }
}
