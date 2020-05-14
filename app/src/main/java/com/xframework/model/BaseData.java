package com.xframework.model;

import com.google.gson.Gson;
import com.xframework.model.WS.BaseWSIn;
import com.xframework.model.WS.GetAllocationInfoIn;
import com.xframework.model.WS.GetAllocationInfoOut;
import com.xframework.model.WS.GetGenerateAllDataIn;
import com.xframework.model.WS.GetProductInfoIn;
import com.xframework.model.WS.GetProductInfoOut;
import com.xframework.model.WS.GetRepairmansOut;
import com.xframework.util.XFrameworkWebServiceUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseData implements Serializable {
    private static List<BaseAllocation> allocations;

    private static List<BaseIntermediateWarehouse> intermediate_warehouses;

    private static Map<String, List<ListItem>> map_setting;

    private static List<BaseProduct> products;

    private static List<BaseRegion> regions;

    private static List<String> repairmans;

    public static void addSet(String setName, List<ListItem> setItems) {
        map_setting.put(setName, setItems);
    }

    public static void clear() {
        map_setting = null;
        products = null;
        intermediate_warehouses = null;
        regions = null;
        allocations = null;
    }

    public static int containsAllocation(String allocationCode) {
        if (allocations == null)
            getData();
        for (int i = 0; i < allocations.size(); i++) {
            if (allocations.get(i).getAllocationCode().equals(allocationCode))
                return i;
        }
        return -1;
    }

    public static int containsIntermediateWarehouse(String intermediateWarehouseCode) {
        if (intermediate_warehouses == null)
            getData();
        for (int i = 0; i < intermediate_warehouses.size(); i++) {
            if (intermediate_warehouses.get(i).getIntermediateWarehouseCode().equals(intermediateWarehouseCode))
                return i;
        }
        return -1;
    }

    public static int containsProduct(int productId) {
        if (products == null)
            getProductData();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == productId)
                return i;
        }
        return -1;
    }

    public static int containsProduct(String productCode) {
        if (products == null)
            getProductData();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductCode().equals(productCode))
                return i;
        }
        return -1;
    }

    public static int containsRegion(String regionCode) {
        if (regions == null)
            getData();
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i).getRegionCode().equals(regionCode))
                return i;
        }
        return -1;
    }

    public static List<BaseAllocation> getAllocations() {
        if (allocations == null)
            getData();
        return allocations;
    }

    private static void getData() {
        new Gson();
        GetAllocationInfoIn getAllocationInfoIn = new GetAllocationInfoIn();
        getAllocationInfoIn.setUserId(LoginUserInfo.getUserId());
        getAllocationInfoIn.setDeviceCode(SystemInfo.getDeviceCode());
        GetAllocationInfoOut getAllocationInfoOut = XFrameworkWebServiceUtil.API_GetAllocationInfo(getAllocationInfoIn);
        if (getAllocationInfoOut.getStatus() == 0) {
            allocations = new ArrayList<>();
            regions = new ArrayList<>();
            intermediate_warehouses = new ArrayList<>();
            for (BaseAllocation baseAllocation : getAllocationInfoOut.getAllocations()) {
                allocations.add(baseAllocation);
                if (containsRegion(baseAllocation.getRegionCode()) == -1) {
                    BaseRegion baseRegion = new BaseRegion();
                    baseRegion.setRegionId(baseAllocation.getRegionId());
                    baseRegion.setRegionCode(baseAllocation.getRegionCode());
                    baseRegion.setIntermediateWarehouseId(baseAllocation.getIntermediateWarehouseId());
                    baseRegion.setFloorCount(baseAllocation.getFloorCount());
                    regions.add(baseRegion);
                }
                if (containsIntermediateWarehouse(baseAllocation.getIntermediateWarehouseCode()) == -1) {
                    BaseIntermediateWarehouse baseIntermediateWarehouse = new BaseIntermediateWarehouse();
                    baseIntermediateWarehouse.setIntermediateWarehouseId(baseAllocation.getIntermediateWarehouseId());
                    baseIntermediateWarehouse.setIntermediateWarehouseCode(baseAllocation.getIntermediateWarehouseCode());
                    baseIntermediateWarehouse.setIntermediateWarehouseName(baseAllocation.getIntermediateWarehouseName());
                    baseIntermediateWarehouse.setStatus(baseAllocation.getStatus());
                    intermediate_warehouses.add(baseIntermediateWarehouse);
                }
            }
        }
    }

    public static List<BaseIntermediateWarehouse> getIntermediateWarehouse() {
        if (intermediate_warehouses == null)
            getData();
        return intermediate_warehouses;
    }

    public static Map<String, List<ListItem>> getMapSetting() {
        if (map_setting == null)
            getSetting();
        return map_setting;
    }

    private static void getProductData() {
        new Gson();
        GetProductInfoIn getProductInfoIn = new GetProductInfoIn();
        getProductInfoIn.setUserId(LoginUserInfo.getUserId());
        getProductInfoIn.setDeviceCode(SystemInfo.getDeviceCode());
        GetProductInfoOut getProductInfoOut = XFrameworkWebServiceUtil.API_GetProductInfo(getProductInfoIn);
        if (getProductInfoOut.getStatus() == 0) {
            products = new ArrayList<>();
            for (BaseProduct baseProduct : getProductInfoOut.getProducts())
                products.add(baseProduct);
        }
    }

    public static List<BaseProduct> getProducts() {
        if (products == null)
            getProductData();
        return products;
    }

    public static List<BaseRegion> getRegions() {
        if (regions == null)
            getData();
        return regions;
    }

    public static List<String> getRepairmans() {
        if (repairmans == null)
            getRepairmansData();
        return repairmans;
    }

    private static void getRepairmansData() {
        new Gson();
        BaseWSIn baseWSIn = new BaseWSIn();
        baseWSIn.setUserId(LoginUserInfo.getUserId());
        baseWSIn.setDeviceCode(SystemInfo.getDeviceCode());
        GetRepairmansOut getRepairmansOut = XFrameworkWebServiceUtil.API_GetRepairmans(baseWSIn);
        if (getRepairmansOut.getStatus() == 0) {
            repairmans = new ArrayList<>();
            for (UserInfo userInfo : getRepairmansOut.getRepairmans())
                repairmans.add(userInfo.getRealName());
        }
    }

    public static List<ListItem> getSet(String setName) {
        if (map_setting == null)
            getSetting();
        return map_setting.containsKey(setName) ? map_setting.get(setName) : null;
    }

    private static void getSetting() {
        try {
            new Gson();
            GetGenerateAllDataIn getGenerateAllDataIn = new GetGenerateAllDataIn();
            getGenerateAllDataIn.setUserId(LoginUserInfo.getUserId());
            getGenerateAllDataIn.setDeviceCode(SystemInfo.getDeviceCode());
            map_setting = XFrameworkWebServiceUtil.API_GetGenerateAllData(getGenerateAllDataIn).getGenerateData();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void removeSet(String setName) {
        map_setting.remove(setName);
    }

    public static void setRepairmans(List<String> repairmans) {
        repairmans = repairmans;
    }
}
