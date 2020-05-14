package com.xframework.util;

import com.google.gson.Gson;
import com.xframework.model.BaseProduct;
import com.xframework.model.LoginUserInfo;
import com.xframework.model.PWProductBarcode;
import com.xframework.model.SystemInfo;
import com.xframework.model.TMGLProBcTree;
import com.xframework.model.WS.CheckBarcodeInfoIn;
import com.xframework.model.WS.CheckBarcodeInfoOut;
import com.xframework.model.WS.CheckSpareBarcodeIn;
import com.xframework.model.WS.CheckSpareBarcodeOut;
import com.xframework.model.WSChint.GetBarcodeOut;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ChintWebServiceUtil {
    private static int timeout = 90*1000;//超时时间
    private static String wsdl_url = "http://10.10.100.24/ChintWebService/ChintWebService.asmx?wsdl";//url地址
    private static String name_sapce = "http://tempuri.org/";//命名空间
    private static int soap_version = SoapEnvelope.VER12;//soap版本



    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        ChintWebServiceUtil.timeout = timeout;
    }

    public static String getWsdl_url() {
        return wsdl_url;
    }

    public static void setWsdl_url(String wsdl_url) {
        ChintWebServiceUtil.wsdl_url = wsdl_url;
    }

    public static String getName_sapce() {
        return name_sapce;
    }

    public static void setName_sapce(String name_sapce) {
        ChintWebServiceUtil.name_sapce = name_sapce;
    }
    public static int getSoap_version() {
        return soap_version;
    }

    public static void setSoap_version(int soap_version) {
        ChintWebServiceUtil.soap_version = soap_version;
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

    public static List<PWProductBarcode> GetBarcodeTreeInfo(String barcode) {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("barcode", barcode);
        String str = callToWebService("GetBarcodeTreeInfo", (HashMap)hashMap);
        TMGLProBcTree[] barcodes = (new Gson()).fromJson(str, TMGLProBcTree[].class);
        ArrayList<PWProductBarcode> pwBarcodes = new ArrayList();
        if (barcode.length() == 19 && barcode.startsWith("0")) {
            int j = barcodes.length;
            for (int i = 0; i < j; i++) {
                TMGLProBcTree tMGLProBcTree = barcodes[i];
                if (tMGLProBcTree.getCBCID().equals(barcode)) {
                    PWProductBarcode pWProductBarcode = new PWProductBarcode();
                    pWProductBarcode.setBarcode(tMGLProBcTree.getLBCID());
                    pWProductBarcode.setAllocationId(-1);
                    pWProductBarcode.setOuterBarcode(tMGLProBcTree.getCBCID());
                    pWProductBarcode.setProductCode(tMGLProBcTree.getMATNR());
                    pWProductBarcode.setSourceId(0);
                    pwBarcodes.add(pWProductBarcode);
                }
            }
        } else {
            int j = barcodes.length;
            for (int i = 0; i < j; i++) {
                TMGLProBcTree tmgl_barcode = barcodes[i];
                if (tmgl_barcode.getLBCID().equals(barcode)) {
                    PWProductBarcode pWProductBarcode = new PWProductBarcode();
                    pWProductBarcode.setBarcode(tmgl_barcode.getLBCID());
                    pWProductBarcode.setAllocationId(-1);
                    pWProductBarcode.setOuterBarcode(tmgl_barcode.getCBCID());
                    pWProductBarcode.setProductCode(tmgl_barcode.getMATNR());
                    pWProductBarcode.setSourceId(0);
                    pwBarcodes.add(pWProductBarcode);
                }
            }
        }
        return pwBarcodes;
    }

    public static GetBarcodeOut GetBatchBarcode(String paramString) {
        GetBarcodeOut getBarcodeOut = new GetBarcodeOut();
        try {
            List<PWProductBarcode> list = GetBarcodeTreeInfo(paramString);
            if (list.size() > 0) {
                CheckBarcodeInfoIn checkBarcodeInfoIn = new CheckBarcodeInfoIn();
                checkBarcodeInfoIn.setUserId(LoginUserInfo.getUserId());
                checkBarcodeInfoIn.setProductCode(((PWProductBarcode)list.get(0)).getProductCode());
                ArrayList<String> arrayList = new ArrayList();
                Iterator<PWProductBarcode> iterator = list.iterator();
                while (iterator.hasNext())
                    arrayList.add(((PWProductBarcode)iterator.next()).getBarcode());
                checkBarcodeInfoIn.setBarcodes(arrayList);
                checkBarcodeInfoIn.setDeviceCode(SystemInfo.getDeviceCode());
                CheckBarcodeInfoOut checkBarcodeInfoOut = XFrameworkWebServiceUtil.API_CheckBarcodeInfo(checkBarcodeInfoIn);
                if (checkBarcodeInfoOut.getStatus() == 0) {
                    if (checkBarcodeInfoOut.getProduct() == null) {
                        getBarcodeOut.setStatus(20102);
                        getBarcodeOut.setMsg("非成品库产品条码，无法入库");
                        return getBarcodeOut;
                    }
                    BaseProduct baseProduct = checkBarcodeInfoOut.getProduct();
                    for (PWProductBarcode pWProductBarcode : checkBarcodeInfoOut.getBarcodes()) {
                        if (!pWProductBarcode.getEnable())
                            for (PWProductBarcode pWProductBarcode1 : list) {
                                if (pWProductBarcode1.getBarcode().equals(pWProductBarcode.getBarcode()))
                                    list.remove(pWProductBarcode1);
                            }
                    }
                    if (list.size() == 0) {
                        getBarcodeOut.setStatus(20109);
                        getBarcodeOut.setMsg("条码已出库");
                        return getBarcodeOut;
                    }
                    for (PWProductBarcode pWProductBarcode : list) {
                        pWProductBarcode.setProductId(baseProduct.getProductId());
                        pWProductBarcode.setProductCode(baseProduct.getProductCode());
                        pWProductBarcode.setProductName(baseProduct.getProductName());
                    }
                } else {
                    getBarcodeOut.setStatus(checkBarcodeInfoOut.getStatus());
                    getBarcodeOut.setMsg(checkBarcodeInfoOut.getMsg());
                    return getBarcodeOut;
                }
            }
            getBarcodeOut.setStatus(0);
            getBarcodeOut.setMsg("执行成功");
            getBarcodeOut.setBarcodes(list);
            return getBarcodeOut;
        } catch (Exception exception) {
            getBarcodeOut.setStatus(29999);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("未知错误：");
            stringBuilder.append(exception.getMessage());
            getBarcodeOut.setMsg(stringBuilder.toString());
            return getBarcodeOut;
        }
    }

    public static GetBarcodeOut GetReceivedBarcode(String barcode) {
        GetBarcodeOut getBarcodeOut = new GetBarcodeOut();
        try {
            List<PWProductBarcode> list = GetBarcodeTreeInfo(barcode);
            if (list.size() > 0) {
                CheckBarcodeInfoIn checkBarcodeInfoIn = new CheckBarcodeInfoIn();
                checkBarcodeInfoIn.setUserId(LoginUserInfo.getUserId());
                checkBarcodeInfoIn.setProductCode(((PWProductBarcode)list.get(0)).getProductCode());
                ArrayList<String> arrayList = new ArrayList();
                Iterator<PWProductBarcode> iterator = list.iterator();
                while (iterator.hasNext())
                    arrayList.add(((PWProductBarcode)iterator.next()).getBarcode());
                checkBarcodeInfoIn.setBarcodes(arrayList);
                checkBarcodeInfoIn.setDeviceCode(SystemInfo.getDeviceCode());
                CheckBarcodeInfoOut checkBarcodeInfoOut = XFrameworkWebServiceUtil.API_CheckBarcodeInfo(checkBarcodeInfoIn);
                if (checkBarcodeInfoOut.getStatus() == 0) {
                    if (checkBarcodeInfoOut.getProduct() == null) {
                        getBarcodeOut.setStatus(20102);
                        getBarcodeOut.setMsg("非成品库产品条码，无法入库");
                        return getBarcodeOut;
                    }
                    BaseProduct baseProduct = checkBarcodeInfoOut.getProduct();
                    for (PWProductBarcode pWProductBarcode : checkBarcodeInfoOut.getBarcodes()) {
                        for (int i = 0; i < list.size(); i++) {
                            PWProductBarcode pWProductBarcode1 = list.get(i);
                            if (pWProductBarcode1.getBarcode().equals(pWProductBarcode.getBarcode())) {
                                list.remove(i);
                                break;
                            }
                            pWProductBarcode1.setProductId(baseProduct.getProductId());
                            pWProductBarcode1.setProductCode(baseProduct.getProductCode());
                            pWProductBarcode1.setProductName(baseProduct.getProductName());
                        }
                    }
                    if (!barcode.startsWith("0") && list.size() == 0) {
                        getBarcodeOut.setStatus(20103);
                        getBarcodeOut.setMsg("条码已入库");
                        return getBarcodeOut;
                    }
                } else {
                    getBarcodeOut.setStatus(checkBarcodeInfoOut.getStatus());
                    getBarcodeOut.setMsg(checkBarcodeInfoOut.getMsg());
                    return getBarcodeOut;
                }
            }
            getBarcodeOut.setStatus(0);
            getBarcodeOut.setMsg("执行成功");
            getBarcodeOut.setBarcodes(list);
            return getBarcodeOut;
        } catch (Exception exception) {
            getBarcodeOut.setStatus(29999);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("未知错误：");
            stringBuilder.append(exception.getMessage());
            getBarcodeOut.setMsg(stringBuilder.toString());
            return getBarcodeOut;
        }
    }

    public static CheckSpareBarcodeOut GetSpareBarcode(String paramString) {
        try {
            CheckSpareBarcodeIn checkSpareBarcodeIn = new CheckSpareBarcodeIn();
            checkSpareBarcodeIn.setUserId(LoginUserInfo.getUserId());
            checkSpareBarcodeIn.setDeviceCode(SystemInfo.getDeviceCode());
            checkSpareBarcodeIn.setBarcode(paramString);
            return XFrameworkWebServiceUtil.API_CheckSpareBarcode(checkSpareBarcodeIn);
        } catch (Exception exception) {
            CheckSpareBarcodeOut checkSpareBarcodeOut = new CheckSpareBarcodeOut();
            checkSpareBarcodeOut.setStatus(29999);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("未知错误：");
            stringBuilder.append(exception.getMessage());
            checkSpareBarcodeOut.setMsg(stringBuilder.toString());
            return checkSpareBarcodeOut;
        }
    }

    public static CheckSpareBarcodeOut GetSpareReceivedBarcode(String paramString) {
        try {
            CheckSpareBarcodeIn checkSpareBarcodeIn = new CheckSpareBarcodeIn();
            checkSpareBarcodeIn.setUserId(LoginUserInfo.getUserId());
            checkSpareBarcodeIn.setDeviceCode(SystemInfo.getDeviceCode());
            checkSpareBarcodeIn.setBarcode(paramString);
            return XFrameworkWebServiceUtil.API_CheckSpareReceivedBarcode(checkSpareBarcodeIn);
        } catch (Exception exception) {
            CheckSpareBarcodeOut checkSpareBarcodeOut = new CheckSpareBarcodeOut();
            checkSpareBarcodeOut.setStatus(29999);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("未知错误：");
            stringBuilder.append(exception.getMessage());
            checkSpareBarcodeOut.setMsg(stringBuilder.toString());
            return checkSpareBarcodeOut;
        }
    }
}
