package com.xframework.model;

import com.google.gson.Gson;
import com.xframework.model.WS.GetAllocationInfoIn;
import com.xframework.model.WS.GetAllocationInfoOut;
import com.xframework.model.WS.GetGenerateAllDataIn;
import com.xframework.model.WS.GetGenerateAllDataOut;
import com.xframework.model.WS.GetProductInfoIn;
import com.xframework.model.WS.GetProductInfoOut;
import com.xframework.util.XFrameworkWebServiceUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseData implements Serializable {
    private static Map<String, List<ListItem>> map_setting;/*系统固定枚举值*/
    private static List<BaseProduct> products;/*产品数据*/
    private static List<BaseIntermediateWarehouse> intermediate_warehouses;/*成品库数据*/
    private static List<BaseRegion> regions;/*区域数据*/
    private static List<BaseAllocation> allocations;/*货位数据*/

    public static List<BaseProduct> getProducts() {
        if (products == null) {
            getProductData();
        }
        return products;
    }

    public static List<BaseIntermediateWarehouse> getIntermediateWarehouse() {
        if (intermediate_warehouses == null) {
            getData();
        }
        return intermediate_warehouses;
    }

    public static List<BaseRegion> getRegions() {
        if (regions == null) {
            getData();
        }
        return regions;
    }

    public static List<BaseAllocation> getAllocations() {
        if (allocations == null) {
            getData();
        }
        return allocations;
    }

    public static Map<String, List<ListItem>> getMapSetting(){
        if(map_setting == null){
            getSetting();
        }
        return map_setting;
    }

    public static List<ListItem> getSet(String set_code) {
        if(map_setting == null){
            getSetting();
        }
        if (map_setting.containsKey(set_code)) {
            return map_setting.get(set_code);
        } else {
            return null;
        }
    }

    public static void addSet(String set_code, List<ListItem> items) {
        map_setting.put(set_code, items);
    }

    public static void removeSet(String set_code) {
        map_setting.remove(set_code);
    }


    /**
     * 获取货位、区域、仓库信息
     */
    private static void getData() {
        Gson gson = new Gson();
        GetAllocationInfoIn in = new GetAllocationInfoIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetAllocationInfoOut ws_out = XFrameworkWebServiceUtil.API_GetAllocationInfo(in);
        if (ws_out.getStatus() == 0) {
            allocations = new ArrayList<>();
            regions = new ArrayList<>();
            intermediate_warehouses = new ArrayList<>();
            for (BaseAllocation allocation : ws_out.getAllocations()) {
                allocations.add(allocation);
                if (containsRegion(allocation.getRegionCode()) == -1) {
                    BaseRegion region = new BaseRegion();
                    region.setRegionId(allocation.getRegionId());
                    region.setRegionCode(allocation.getRegionCode());
                    region.setIntermediateWarehouseId(allocation.getIntermediateWarehouseId());
                    region.setFloorCount(allocation.getFloorCount());
                    regions.add(region);
                }
                if (containsIntermediateWarehouse(allocation.getIntermediateWarehouseCode()) == -1) {
                    BaseIntermediateWarehouse intermediate_warehouse = new BaseIntermediateWarehouse();
                    intermediate_warehouse.setIntermediateWarehouseId(allocation.getIntermediateWarehouseId());
                    intermediate_warehouse.setIntermediateWarehouseCode(allocation.getIntermediateWarehouseCode());
                    intermediate_warehouse.setIntermediateWarehouseName(allocation.getIntermediateWarehouseName());
                    intermediate_warehouse.setStatus(allocation.getStatus());
                    intermediate_warehouses.add(intermediate_warehouse);
                }
            }
        }
    }

    /**
     * 获取货位、区域、仓库信息
     */
    private static void getSetting() {
        //设置参数
        try {
            Gson gson = new Gson();
            GetGenerateAllDataIn in = new GetGenerateAllDataIn();
            in.setUserId(LoginUserInfo.getUserId());
            in.setDeviceCode(SystemInfo.getDeviceCode());
            GetGenerateAllDataOut ws_out = XFrameworkWebServiceUtil.API_GetGenerateAllData(in);
            map_setting = ws_out.getGenerateData();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    /**
     * 获取产品数据
     */
    private static void getProductData() {
        Gson gson = new Gson();
        GetProductInfoIn in = new GetProductInfoIn();
        in.setUserId(LoginUserInfo.getUserId());
        in.setDeviceCode(SystemInfo.getDeviceCode());
        GetProductInfoOut ws_out = XFrameworkWebServiceUtil.API_GetProductInfo(in);
        if (ws_out.getStatus() == 0) {
            products = new ArrayList<>();
            for (BaseProduct product : ws_out.getProducts()) {
                products.add(product);
            }
        }
    }

    public static int containsAllocation(String allocation_code) {
        if (allocations == null) {
            getData();
        }
        for (int i = 0; i < allocations.size(); i++) {
            if (allocations.get(i).getAllocationCode().equals(allocation_code)) {
                return i;
            }
        }
        return -1;
    }

    public static int containsRegion(String region_code) {
        if (regions == null) {
            getData();
        }
        for (int i = 0; i < regions.size(); i++) {
            if (regions.get(i).getRegionCode().equals(region_code)) {
                return i;
            }
        }
        return -1;
    }

    public static int containsIntermediateWarehouse(String intermediate_warehouse_code) {
        if (intermediate_warehouses == null) {
            getData();
        }
        for (int i = 0; i < intermediate_warehouses.size(); i++) {
            if (intermediate_warehouses.get(i).getIntermediateWarehouseCode().equals(intermediate_warehouse_code)) {
                return i;
            }
        }
        return -1;
    }

    public static int containsProduct(String product_code) {
        if (products == null) {
            getProductData();
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductCode().equals(product_code)) {
                return i;
            }
        }
        return -1;
    }

    public static int containsProduct(int product_id) {
        if (products == null) {
            getProductData();
        }
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product_id) {
                return i;
            }
        }
        return -1;
    }

    public static void clear(){
        map_setting = null;
        products = null;
        intermediate_warehouses = null;
        regions = null;
        allocations = null;
    }
}
